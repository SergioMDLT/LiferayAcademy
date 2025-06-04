import { inject, Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { AuthService as Auth0Service, User } from '@auth0/auth0-angular';
import { BehaviorSubject, catchError, filter, from, map, Observable, of, switchMap, take, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly isBrowser: boolean;
  private readonly auth0?: Auth0Service;

  private readonly userSubject = new BehaviorSubject<User | null>(null);
  public user$ = this.userSubject.asObservable();

  private readonly isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(
    @Inject(PLATFORM_ID)
    private readonly platformId: Object,
    private readonly http: HttpClient,
    private readonly router: Router
  ) {
    this.isBrowser = isPlatformBrowser(platformId);

    if (this.isBrowser) {
      this.auth0 = inject(Auth0Service);

      this.auth0.user$.subscribe(user => {
        this.userSubject.next(user ?? null);
        if (user) {
          this.getToken().subscribe(token => {
            if (token) this.registerUser(user);
          });
        }
      });

      this.auth0.isAuthenticated$.subscribe(isAuth => {
        this.isAuthenticatedSubject.next(isAuth);
      });
    }
  }

  private registerUser(user: User): void {
    const userDTO = { auth0Id: user.sub, email: user.email };
    this.http.post('http://localhost:8080/users/register', userDTO).subscribe();
  }

  login(): void {
    if (!this.isBrowser || !this.auth0) return;
    this.auth0.loginWithRedirect({
      authorizationParams: {
        redirect_uri: 'http://localhost:4200/callback'
      },
      appState: { target: '/main' }
    });
  }

  logout(): void {
    if (this.isBrowser && this.auth0) {
      this.auth0.logout({
        logoutParams: { returnTo: 'http://localhost:4200' }
      });
    }
  }

  getUser(): Observable<User | null> {
    return this.isBrowser && this.auth0 ? this.auth0.user$.pipe(map(u => u ?? null)) : of(null);
  }

  getToken(): Observable<string> {
    return this.isBrowser && this.auth0
      ? from(this.auth0.getAccessTokenSilently()).pipe(
          catchError(() => of(''))
        )
      : of('');
  }

  handleRedirectCallback$(): Observable<void> {
    return from(this.auth0!.handleRedirectCallback()).pipe(
      switchMap(result => {
        const target = result?.appState?.target ?? '/main';
        return this.auth0!.isAuthenticated$.pipe(
          filter(authenticated => authenticated),
          take(1),
          tap(() => {
            this.router.navigate([target]);
          })
        );
      }),
      catchError(err => {
        console.error('Error en callback:', err);
        this.router.navigate(['/main']);
        return of(void 0);
      }),
      map(() => void 0)
    );
  }

  public get loading$(): Observable<boolean> {
    return this.isBrowser && this.auth0 ? this.auth0.isLoading$ : of(false);
  }
}

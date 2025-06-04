import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CanActivate } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, filter, map, switchMap, take, tap } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { ToastService } from '../../shared/services/toast.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  private readonly isBrowser: boolean;

  constructor(
    private readonly auth: AuthService,
    @Inject(PLATFORM_ID) private readonly platformId: Object
  ) {
    this.isBrowser = isPlatformBrowser(platformId);
  }

  canActivate(): Observable<boolean> {
    if (!this.isBrowser) {
      return of(false);
    }

    return this.auth.loading$.pipe(
      filter(loading => !loading),
      take(1),
      switchMap(() => this.auth.isAuthenticated$),
      tap(isAuthenticated => {
        if (!isAuthenticated) {
          console.warn("User not authenticated, trying to find session...");

          this.auth.getToken().subscribe({
            next: (token) => {
              if (!token) {
                console.warn("Session could not be found, redirecting to login...");
                this.auth.login();
              }
            },
            error: (err) => {
              console.error("Error reloading token:", err);
              this.auth.login();
            }
          });
        }
      }),
      map(isAuthenticated => isAuthenticated),
      catchError(error => {
        console.error("Error verifying authentication:", error);
        return of(false);
      })
    );
  }
}

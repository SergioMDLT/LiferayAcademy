import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { AuthService } from './auth.service';
import { ToastService } from '../../shared/services/toast.service';
import { Observable, of } from 'rxjs';
import { catchError, map, take } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AdminGuard implements CanActivate {

  private readonly rolesNamespace = 'https://liferayacademy.com/claims/';

  constructor(
    private readonly authService: AuthService,
    private readonly toastService: ToastService
  ) {}

  canActivate(): Observable<boolean> {
    return this.authService.getUser().pipe(
      take(1),
      map(user => {
        const roles = user?.[this.rolesNamespace + 'roles'] ?? [];
        const isAdmin = roles.includes('admin');
        if (!isAdmin) {
          this.toastService.showError('Access denied: admin only');
          console.warn('Blocked non-admin user');
        }
        return isAdmin;
      }),
      catchError(err => {
        console.error('Error verifying admin role', err);
        return of(false);
      })
    );
  }
}

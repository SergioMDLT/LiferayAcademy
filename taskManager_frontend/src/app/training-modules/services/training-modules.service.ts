import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, EMPTY, Observable, of, switchMap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ToastService } from '../../shared/services/toast.service';
import { AuthService } from '../../auth/services/auth.service';
import { TrainingModule } from '../models/TrainingModule';

@Injectable({ providedIn: 'root' })
export class TrainingModulesService {

  private readonly baseUrl: string = `${environment.apiUrl}/training-modules`;

  constructor(
    private readonly authService: AuthService,
    private readonly http: HttpClient,
    private readonly toastService: ToastService
  ) {}

  getDefaultModules(): Observable<TrainingModule[]> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        return this.http.get<TrainingModule[]>(`${this.baseUrl}/default`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error loading default modules: ${error.message}`);
        return of([]);
      })
    );
  }

  getModules(): Observable<TrainingModule[]> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        return this.http.get<TrainingModule[]>(this.baseUrl, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error loading modules: ${error.message}`);
        return of([]);
      })
    );
  }

  createModule(data: { name: string; description: string }): Observable<TrainingModule | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        return this.http.post<TrainingModule>(this.baseUrl, data, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          })
        });
      }),
      catchError(error => {
        this.toastService.showError('Failed to create training module');
        return of(null);
      })
    );
  }

  updateModule(id: number, data: { name: string; description: string }): Observable<TrainingModule | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        return this.http.put<TrainingModule>(`${this.baseUrl}/${id}`, data, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          })
        });
      }),
      catchError(error => {
        this.toastService.showError('Failed to update training module');
        return of(null);
      })
    );
  }

  deleteModule(id: number): Observable<void> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        return this.http.delete<void>(`${this.baseUrl}/${id}`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Failed to delete module: ${error.message}`);
        return EMPTY;
      })
    );
  }

  getModuleById(id: number): Observable<TrainingModule | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        return this.http.get<TrainingModule>(`${this.baseUrl}/${id}`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error fetching module with id ${id}`);
        return of(null);
      })
    );
  }

}

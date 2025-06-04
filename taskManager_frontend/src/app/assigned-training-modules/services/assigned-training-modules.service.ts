import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, EMPTY, Observable, of, switchMap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ToastService } from '../../shared/services/toast.service';
import { AuthService } from '../../auth/services/auth.service';
import { AssignedTrainingModuleView } from '../models/AssignedTrainingModuleView';
import { AssignedTrainingModule } from '../models/AssignedTrainingModule';

@Injectable({ providedIn: 'root' })
export class AssignedTrainingModulesService {

  private readonly baseUrl: string = `${environment.apiUrl}/assigned-training-modules`;

  constructor(
    private readonly authService:  AuthService,
    private readonly http:         HttpClient,
    private readonly toastService: ToastService
  ) {}

  getAssignedModules(): Observable<AssignedTrainingModule[]> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("No token found");
          return of([]);
        }

        return this.http.get<AssignedTrainingModule[]>(`${this.baseUrl}`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(err => {
        this.toastService.showError("Error fetching assigned modules");
        return of([]);
      })
    );
  }

  getAllAssignedModules(): Observable<AssignedTrainingModuleView[]> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("No token found");
          return of([]);
        }

        return this.http.get<AssignedTrainingModuleView[]>(`${this.baseUrl}/all`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(err => {
        this.toastService.showError("Error fetching all assigned modules");
        return of([]);
      })
    );
  }

  assignModule(trainingModuleId: number): Observable<AssignedTrainingModule | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        const body = { trainingModuleId };

        return this.http.post<AssignedTrainingModule>(this.baseUrl, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error assigning module: ${error.message}`);
        return of(null);
      })
    );
  }

  assignModuleToUser(trainingModuleId: number, userId: number): Observable<AssignedTrainingModule | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        const body = { trainingModuleId, userId };

        return this.http.post<AssignedTrainingModule>(`${this.baseUrl}/admin`, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error assigning module to user: ${error.message}`);
        return of(null);
      })
    );
  }

  unassignModule(id: number): Observable<void> {
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
        this.toastService.showError(`Error unassigning module: ${error.message}`);
        return EMPTY;
      })
    );
  }

  updateModuleCompletion(id: number, completed: boolean): Observable<AssignedTrainingModule | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError('User token not found. Request not sent');
          return EMPTY;
        }

        const body = { completed };

        return this.http.put<AssignedTrainingModule>(`${this.baseUrl}/${id}/completion`, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error updating module completion: ${error.message}`);
        return of(null);
      })
    );
  }

}

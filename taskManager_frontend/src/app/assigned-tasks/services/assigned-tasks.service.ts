import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, EMPTY, Observable, of, switchMap, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AssignedTask } from '../models/assigned-task';
import { ToastService } from '../../shared/services/toast.service';
import { AuthService } from '../../auth/services/auth.service';

@Injectable({ providedIn: 'root' })
export class AssignedTasksService {
  private readonly baseUrl: string = `${environment.apiUrl}/assigned-tasks`;

  constructor(
    private readonly authService: AuthService,
    private readonly http: HttpClient,
    private readonly toastService: ToastService
  ) {}

  getAssignedTaskById(id: number): Observable<AssignedTask | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return of(null);
        }

        return this.http.get<AssignedTask>(`${this.baseUrl}/${id}`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error fetching assigned task by ID: ${error.message}`);
        return of(null);
      })
    );
  }

  getAssignedTasks(params?: {
    completed?: boolean;
    title?: string;
    trainingModuleId?: number;
    page?: number;
    size?: number;
    sort?: string;
    admin?: boolean;
  }): Observable<{ content: AssignedTask[]; totalPages: number; totalElements: number }> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        return this.http.get<{ content: AssignedTask[]; totalPages: number; totalElements: number }>(this.baseUrl, {
          params,
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error fetching assigned tasks: ${error.message}`);
        return of({ content: [], totalPages: 0, totalElements: 0 });
      })
    );
  }

  createAssignedTask(taskId: number): Observable<AssignedTask | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return of(null);
        }

        const body = { taskId };

        return this.http.post<AssignedTask>(this.baseUrl, body, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError("Failed to create assigned task");
        return of(null);
      })
    );
  }

  assignTaskToUser(taskId: number, userId: number): Observable<AssignedTask | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return of(null);
        }

        const body = { taskId, userId };

        return this.http.post<AssignedTask>(`${this.baseUrl}/admin`, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error assigning task: ${error.message}`);
        return of(null);
      })
    );
  }

  deleteAssignedTask(id: number): Observable<void> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        return this.http.delete<void>(`${this.baseUrl}/${id}`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error deleting assigned task: ${error.message}`);
        return throwError(() => new Error('Failed to delete assigned task'));
      })
    );
  }

  updateAssignedTaskCompletion(id: number): Observable<AssignedTask> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        return this.http.patch<AssignedTask>(`${this.baseUrl}/${id}/completion`, {}, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error updating assigned task completion: ${error.message}`);
        return throwError(() => new Error("Failed to update completion"));
      })
    );
  }

  updateAssignedTaskModulePriority(id: number, modulePriority: number): Observable<AssignedTask> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        const body = { modulePriority };

        return this.http.patch<AssignedTask>(`${this.baseUrl}/${id}/priority/module`, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error updating module priority: ${error.message}`);
        return throwError(() => new Error("Failed to update module priority"));
      })
    );
  }

  updateAssignedTaskAbsolutePriority(id: number, absolutePriority: number): Observable<AssignedTask> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        const body = { absolutePriority };

        return this.http.patch<AssignedTask>(`${this.baseUrl}/${id}/priority/absolute`, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error updating absolute priority: ${error.message}`);
        return throwError(() => new Error("Failed to update absolute priority"));
      })
    );
  }

}

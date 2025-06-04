import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, EMPTY, map, Observable, of, switchMap, throwError } from 'rxjs';
import { Task } from '../models/task';
import { environment } from '../../../environments/environment';
import { ToastService } from '../../shared/services/toast.service';
import { AuthService } from '../../auth/services/auth.service';

@Injectable({ providedIn: 'root' })
export class TasksService {

  private readonly baseUrl: string = `${environment.apiUrl}/tasks`;

  constructor(
    private readonly authService: AuthService,
    private readonly http: HttpClient,
    private readonly toastService: ToastService
  ) {}

  getTaskById(id: number): Observable<Task | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return of(null);
        }

        return this.http.get<Task>(`${this.baseUrl}/${id}`, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error fetching task by ID: ${error.message}`);
        return of(null);
      })
    );
  }

  getTasks(params?: {
    title?: string;
    trainingModuleId?: number;
    page?: number;
    size?: number;
    sort?: string;
  }): Observable<any> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        return this.http.get<any>(this.baseUrl, {
          params,
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error fetching tasks: ${error.message}`);
        return of({ content: [], totalPages: 0, totalElements: 0 });
      })
    );
  }

  createTask(title: string, description: string, trainingModuleId: number): Observable<Task | null> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return of(null);
        }

        const body = { title, description, trainingModuleId };

        return this.http.post<Task>(this.baseUrl, body, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError("Failed to create task");
        return of(null);
      })
    );
  }

  deleteTask(id: number): Observable<void> {
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
        this.toastService.showError(`Error deleting task: ${error.message}`);
        return throwError(() => new Error('Failed to delete task'));
      })
    );
  }

  updateTask(id: number, title: string, description: string, trainingModuleId: number): Observable<void> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return EMPTY;
        }

        const body = { title, description, trainingModuleId };

        return this.http.put<void>(`${this.baseUrl}/${id}`, body, {
          headers: new HttpHeaders({
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error updating task: ${error.message}`);
        return throwError(() => new Error("Failed to update task"));
      })
    );
  }

  getAllTasks(params: {
    title?: string;
    trainingModuleId?: number;
    page?: number;
    size?: number;
    sort?: string;
  } = { page: 0, size: 10, sort: 'id' }): Observable<any> {
    return this.authService.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toastService.showError("User token not found. Request not sent");
          return of({ content: [], totalPages: 0, totalElements: 0 });
        }

        let httpParams = new HttpParams();
        if (params.title) httpParams = httpParams.set('title', params.title);
        if (params.trainingModuleId != null) httpParams = httpParams.set('trainingModuleId', params.trainingModuleId.toString());
        if (params.page != null) httpParams = httpParams.set('page', params.page.toString());
        if (params.size != null) httpParams = httpParams.set('size', params.size.toString());
        if (params.sort) httpParams = httpParams.set('sort', params.sort);

        return this.http.get<any>(this.baseUrl, {
          params: httpParams,
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(error => {
        this.toastService.showError(`Error fetching tasks: ${error.message}`);
        return of({ content: [], totalPages: 0, totalElements: 0 });
      })
    );
  }

}

import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { AuthService } from "../../auth/services/auth.service";
import { ToastService } from "../../shared/services/toast.service";
import { catchError, Observable, of, switchMap } from "rxjs";
import { User } from "../models/user";

@Injectable({ providedIn: 'root' })
export class UsersService {

  private readonly baseUrl = `${environment.apiUrl}/users`;

  constructor(
    private readonly http: HttpClient,
    private readonly auth: AuthService,
    private readonly toast: ToastService
  ) {}

  getUsers(): Observable<User[]> {
    return this.auth.getToken().pipe(
      switchMap(token => {
        if (!token) {
          this.toast.showError('Token not found');
          return of([]);
        }

        return this.http.get<User[]>(this.baseUrl, {
          headers: new HttpHeaders({ Authorization: `Bearer ${token}` })
        });
      }),
      catchError(err => {
        this.toast.showError('Error fetching users');
        return of([]);
      })
    );
  }

}

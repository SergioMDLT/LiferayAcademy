import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { Router } from '@angular/router';
import { filter, take } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Task Manager';

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    if (this.router.url.startsWith('/callback')) return;

    this.authService.isAuthenticated$
      .pipe(filter(Boolean), take(1))
      .subscribe(() => {
        this.router.navigate(['/main']);
      });
  }

}

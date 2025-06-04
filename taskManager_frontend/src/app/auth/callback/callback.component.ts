import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { filter, switchMap, take } from 'rxjs';

@Component({
  selector: 'app-callback',
  standalone: false,
  templateUrl: './callback.component.html',
  styleUrl: './callback.component.scss'
})
export class CallbackComponent {
  constructor(private readonly auth: AuthService) {}

  ngOnInit(): void {
    this.auth.handleRedirectCallback$().pipe(
      switchMap(() => this.auth.isAuthenticated$),
      filter(authenticated => authenticated),
      take(1)
    ).subscribe(() => {
      console.log('Callback completed and user authenticated');
    });
  }

}

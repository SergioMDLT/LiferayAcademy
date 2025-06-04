import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/services/auth.service';

@Component({
  selector: 'shared-sidebar',
  standalone: false,
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent implements OnInit {

  isAdmin = false;

  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    this.auth.user$.subscribe(user => {
      const namespace = 'https://liferayacademy.com/claims/';
      this.isAdmin = user?.[namespace + 'roles']?.includes('admin') ?? false;
    });
  }

  getUsernameFromEmail(email: string): string {
    if (!email) return '';
    return email.split('@')[0];
  }

}

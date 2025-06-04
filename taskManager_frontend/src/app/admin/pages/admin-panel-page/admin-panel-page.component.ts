import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-panel-page',
  standalone: false,
  templateUrl: './admin-panel-page.component.html',
  styleUrl: './admin-panel-page.component.scss'
})
export class AdminPanelPageComponent implements OnInit {
  public isLoading = true;

  ngOnInit(): void {
    setTimeout(() => {
      this.isLoading = false;
    }, 500);
  }

}

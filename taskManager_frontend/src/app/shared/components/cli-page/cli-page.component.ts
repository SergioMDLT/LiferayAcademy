import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cli-page',
  standalone: false,
  templateUrl: './cli-page.component.html',
  styleUrl: './cli-page.component.scss'
})
export class CliPageComponent implements OnInit {
  public isLoading = true;

  ngOnInit(): void {
    setTimeout(() => {
      this.isLoading = false;
    }, 1000); // 1 segundo de simulación de carga
  }

  onSubmit(event: Event): void {
    event.preventDefault();
    // Aquí no hacemos nada, es solo para evitar submit real
  }

}

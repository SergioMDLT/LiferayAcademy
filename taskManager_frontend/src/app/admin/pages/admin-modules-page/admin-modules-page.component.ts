import { Component, OnInit } from '@angular/core';
import { TrainingModulesService } from '../../../training-modules/services/training-modules.service';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';
import { ToastService } from '../../../shared/services/toast.service';

@Component({
  selector: 'admin-modules-page',
  standalone: false,
  templateUrl: './admin-modules-page.component.html',
  styleUrls: ['./admin-modules-page.component.scss']
})
export class AdminModulesPageComponent implements OnInit {

  modules: TrainingModule[] = [];

  constructor(
    private readonly trainingService: TrainingModulesService,
    private readonly toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.loadModules();
  }

  loadModules(): void {
    this.trainingService.getModules().subscribe({
      next: modules => this.modules = modules,
      error: () => this.toastService.showError('Error loading modules')
    });
  }

  onModuleFormSubmit(data: { name: string; description: string }): void {
    this.trainingService.createModule(data).subscribe({
      next: () => {
        this.toastService.showSuccess("Módulo creado correctamente");
        this.loadModules();
      },
      error: () => this.toastService.showError("Error creando módulo")
    });
  }

  updateModule(id: number, data: { name: string; description: string }): void {
    this.trainingService.updateModule(id, data).subscribe({
      next: () => {
        this.toastService.showSuccess("Módulo actualizado");
        this.loadModules();
      },
      error: () => this.toastService.showError("Error actualizando módulo")
    });
  }

  deleteModule(id: number): void {
    if (confirm('¿Eliminar este módulo?')) {
      this.trainingService.deleteModule(id).subscribe({
        next: () => {
          this.toastService.showSuccess("Módulo eliminado");
          this.loadModules();
        },
        error: () => this.toastService.showError("Error eliminando módulo")
      });
    }
  }
}

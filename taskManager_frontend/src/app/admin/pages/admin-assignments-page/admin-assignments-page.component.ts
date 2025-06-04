import { Component, OnInit } from '@angular/core';
import { AssignedTrainingModulesService } from '../../../assigned-training-modules/services/assigned-training-modules.service';
import { AssignedTrainingModuleView } from '../../../assigned-training-modules/models/AssignedTrainingModuleView';
import { TrainingModulesService } from '../../../training-modules/services/training-modules.service';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';
import { UsersService } from '../../../users/services/users.service';
import { User } from '../../../users/models/user';
import { ToastService } from '../../../shared/services/toast.service';

@Component({
  selector: 'admin-assignments-page',
  standalone: false,
  templateUrl: './admin-assignments-page.component.html',
  styleUrls: ['./admin-assignments-page.component.scss']
})
export class AdminAssignmentsPageComponent implements OnInit {
  assignments: AssignedTrainingModuleView[] = [];
  modules: TrainingModule[] = [];
  users: User[] = [];

  constructor(
    private readonly assignedService: AssignedTrainingModulesService,
    private readonly trainingService: TrainingModulesService,
    private readonly usersService: UsersService,
    private readonly toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.loadAssignments();
    this.loadUsers();
    this.loadModules();
  }

  loadAssignments(): void {
    this.assignedService.getAllAssignedModules().subscribe({
      next: data => this.assignments = data,
      error: () => this.toastService.showError('Error al cargar asignaciones')
    });
  }

  loadModules(): void {
    this.trainingService.getModules().subscribe({
      next: data => this.modules = data,
      error: () => this.toastService.showError('Error al cargar módulos')
    });
  }

  loadUsers(): void {
    this.usersService.getUsers().subscribe({
      next: data => this.users = data,
      error: () => this.toastService.showError('Error al cargar usuarios')
    });
  }

  onAssignModuleSubmit(data: { userId: number; trainingModuleId: number }): void {
    this.assignedService.assignModuleToUser(data.trainingModuleId, data.userId).subscribe(() => {
      this.toastService.showSuccess('Módulo asignado');
      this.loadAssignments();
    });
  }

  onCompletionToggle(id: number, completed: boolean): void {
    this.assignedService.updateModuleCompletion(id, completed).subscribe(() => {
      this.toastService.showSuccess('Estado de completitud actualizado');
      this.loadAssignments();
    });
  }

  unassignModule(id: number): void {
    if (confirm('¿Desasignar este módulo?')) {
      this.assignedService.unassignModule(id).subscribe(() => {
        this.toastService.showSuccess('Módulo desasignado');
        this.loadAssignments();
      });
    }
  }

  getModuleName(id: number): string {
    return this.modules.find(m => m.id === id)?.name ?? 'Cargando...';
  }

  getUserEmail(id: number): string {
    return this.users.find(u => u.id === id)?.email ?? 'Cargando...';
  }
}

import { Component, OnInit } from '@angular/core';
import { TrainingModulesService } from '../../../training-modules/services/training-modules.service';
import { AssignedTrainingModulesService } from '../../../assigned-training-modules/services/assigned-training-modules.service';
import { ToastService } from '../../../shared/services/toast.service';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';

@Component({
  selector: 'app-training-modules-page',
  standalone: false,
  templateUrl: './training-modules-page.component.html',
  styleUrl: './training-modules-page.component.scss'
})
export class TrainingModulesPageComponent implements OnInit {

  public modules: TrainingModule[] = [];
  public isLoading = true;

  constructor(
    private readonly trainingService: TrainingModulesService,
    private readonly assignedModulesService: AssignedTrainingModulesService,
    private readonly toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.trainingService.getModules().subscribe({
      next: modules => {
        setTimeout(() => {
          this.modules = modules;
          this.isLoading = false;
        }, 500);
      },
      error: () => this.isLoading = false
    });
  }

  subscribeToModule(moduleId: number): void {
    this.assignedModulesService.assignModule(moduleId).subscribe({
      next: assignedModule => {
        if (assignedModule) {
          this.toastService.showSuccess('Subscribed to module successfully!');
        } else {
          this.toastService.showError('Subscription failed.');
        }
      },
      error: () => {
        this.toastService.showError('Error subscribing to module.');
      }
    });
  }

}

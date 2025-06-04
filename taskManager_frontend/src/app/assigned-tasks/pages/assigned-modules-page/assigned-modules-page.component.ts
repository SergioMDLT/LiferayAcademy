import { Component, OnInit } from '@angular/core';
import { AssignedTrainingModulesService } from '../../../assigned-training-modules/services/assigned-training-modules.service';
import { Router } from '@angular/router';
import { AssignedTrainingModule } from '../../../assigned-training-modules/models/AssignedTrainingModule';

@Component({
  selector: 'app-assigned-modules-page',
  standalone: false,
  templateUrl: './assigned-modules-page.component.html',
  styleUrl: './assigned-modules-page.component.scss'
})
export class AssignedModulesPageComponent implements OnInit {
  public assignedModules: AssignedTrainingModule[] = [];
  public isLoading: boolean = false;

  constructor(
    private readonly assignedModulesService: AssignedTrainingModulesService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.assignedModulesService.getAssignedModules().subscribe({
      next: modules => {
        setTimeout(() => {
          this.assignedModules = modules;
          this.isLoading = false;
        }, 500);
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  goToModuleTasks(moduleId: number): void {
    this.router.navigate(['/tasks-by-module', moduleId]);
  }
}

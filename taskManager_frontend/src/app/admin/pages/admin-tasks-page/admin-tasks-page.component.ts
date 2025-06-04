import { Component, HostListener, OnInit } from '@angular/core';
import { Task } from '../../../tasks/models/task';
import { TasksService } from '../../../tasks/services/tasks.service';
import { ToastService } from '../../../shared/services/toast.service';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';
import { TrainingModulesService } from '../../../training-modules/services/training-modules.service';

@Component({
  selector: 'app-admin-tasks-page',
  standalone: false,
  templateUrl: './admin-tasks-page.component.html',
  styleUrl: './admin-tasks-page.component.scss'
})
export class AdminTasksPageComponent implements OnInit {

  public tasks: Task[] = [];
  public trainingModules: TrainingModule[] = [];
  public isLoading = false;
  public totalPages = 0;

  private readonly pageSize = 10;
  private currentPage = 0;

  constructor(
    private readonly tasksService: TasksService,
    private readonly toastService: ToastService,
    private readonly trainingModulesService: TrainingModulesService
  ) {}

  ngOnInit(): void {
    this.loadModules();
    this.loadTasks();
  }

  loadModules(): void {
    this.trainingModulesService.getModules().subscribe({
      next: modules => this.trainingModules = modules,
      error: () => this.toastService.showError("Error loading training modules")
    });
  }

  loadTasks(): void {
    if (this.isLoading || (this.currentPage >= this.totalPages && this.totalPages !== 0)) return;

    this.isLoading = true;

    this.tasksService.getAllTasks({
      page: this.currentPage,
      size: this.pageSize,
      sort: 'id'
    }).subscribe({
      next: response => {
        this.tasks = [...this.tasks, ...response.content];
        this.totalPages = response.totalPages;
        this.currentPage++;
        this.isLoading = false;
      },
      error: () => {
        this.toastService.showError('Error loading tasks');
        this.isLoading = false;
      }
    });
  }

  @HostListener('window:scroll', [])
  onScroll(): void {
    const threshold = 100;
    const position = window.innerHeight + window.scrollY;
    const height = document.body.offsetHeight;

    if (position >= height - threshold && !this.isLoading) this.loadTasks();
  }

  onCreateTask(data: { title: string; description: string; trainingModuleId: number }): void {
    this.tasksService.createTask(data.title, data.description, data.trainingModuleId).subscribe({
      next: task => {
        this.toastService.showSuccess("Task created");
        this.loadTasks();
      },
      error: () => this.toastService.showError("Error creating task")
    });
  }

  deleteTask(id: number): void {
    if (confirm('¿Eliminar esta tarea?')) {
      this.tasksService.deleteTask(id).subscribe(() => {
        this.tasks = this.tasks.filter(t => t.id !== id);
      });
    }
  }

  getModuleName(id: number): string {
    return this.trainingModules.find(mod => mod.id === id)?.name ?? 'Cargando...';
  }

}

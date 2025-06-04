import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AssignedTask } from '../../models/assigned-task';
import { AssignedTasksService } from '../../services/assigned-tasks.service';

@Component({
  selector: 'app-tasks-by-module-page',
  standalone: false,
  templateUrl: './tasks-by-module-page.component.html',
  styleUrl: './tasks-by-module-page.component.scss'
})
export class TasksByModulePageComponent implements OnInit {
  public tasks: AssignedTask[] = [];
  public isLoading: boolean = false;
  public moduleId!: number;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly tasksService: AssignedTasksService
  ) {}

  ngOnInit(): void {
    this.moduleId = +this.route.snapshot.paramMap.get('id')!;
    this.loadTasks();
  }

  loadTasks(): void {
    this.isLoading = true;
    this.tasksService.getAssignedTasks({
      trainingModuleId: this.moduleId,
      completed: false,
      sort: 'modulePriority'
    }).subscribe({
      next: res => {
        this.tasks = res.content;
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  removeCompletedTask(task: AssignedTask): void {
    this.tasks = this.tasks
      .filter(t => t.id !== task.id)
      .map((t, index) => ({ ...t, modulePriority: index + 1 }));
  }
}

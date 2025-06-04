import { Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AssignedTask } from '../../../assigned-tasks/models/assigned-task';
import { AssignedTasksService } from '../../../assigned-tasks/services/assigned-tasks.service';
import { UsersService } from '../../../users/services/users.service';
import { TasksService } from '../../../tasks/services/tasks.service';
import { User } from '../../../users/models/user';
import { Task } from '../../../tasks/models/task';

@Component({
  selector: 'app-admin-task-assignments-page',
  standalone: false,
  templateUrl: './admin-task-assignments-page.component.html',
  styleUrl: './admin-task-assignments-page.component.scss'
})
export class AdminTaskAssignmentsPageComponent implements OnInit {

  assignments: AssignedTask[] = [];
  users: User[] = [];
  tasks: Task[] = [];

  taskPage = 0;
  taskPageSize = 10;
  taskTotalPages = 1;

  assignmentPage = 0;
  assignmentPageSize = 10;
  assignmentTotalPages = 1;
  isLoading = false;

  completionForm: FormGroup;

  constructor(
    private readonly fb: FormBuilder,
    private readonly assignedTaskService: AssignedTasksService,
    private readonly usersService: UsersService,
    private readonly tasksService: TasksService
  ) {
    this.completionForm = this.fb.group({
      assignedTaskId: ['', Validators.required],
      completed: [false]
    });
  }

  ngOnInit(): void {
    this.resetAssignmentPagination();
    this.loadUsers(); // se cargan users primero
    this.loadTasks();
  }

  private resetAssignmentPagination(): void {
    this.assignmentPage = 0;
    this.assignmentTotalPages = 0;
    this.assignments = [];
  }

  loadUsers(): void {
    this.usersService.getUsers().subscribe({
      next: data => {
        this.users = data;
        this.loadAssignments();
      },
      error: () => console.error('Error loading users')
    });
  }

  loadAssignments(): void {
    if (this.isLoading || (this.assignmentTotalPages !== 0 && this.assignmentPage >= this.assignmentTotalPages)) return;

    this.isLoading = true;

    this.assignedTaskService.getAssignedTasks({
      admin: true,
      page: this.assignmentPage,
      size: this.assignmentPageSize,
      sort: 'id'
    }).subscribe({
      next: data => {
        this.assignments = [...this.assignments, ...data.content];
        this.assignmentPage++;
        this.assignmentTotalPages = data.totalPages;
        this.isLoading = false;
      },
      error: () => {
        console.error('Error loading assigned tasks');
        this.isLoading = false;
      }
    });
  }

  loadTasks(): void {
    this.tasksService.getAllTasks({ page: this.taskPage, size: this.taskPageSize }).subscribe({
      next: data => {
        this.tasks = data.content;
        this.taskTotalPages = data.totalPages;
      },
      error: () => console.error('Error loading tasks')
    });
  }

  nextTaskPage(): void {
    if (this.taskPage + 1 < this.taskTotalPages) {
      this.taskPage++;
      this.loadTasks();
    }
  }

  prevTaskPage(): void {
    if (this.taskPage > 0) {
      this.taskPage--;
      this.loadTasks();
    }
  }

  onAssignTaskSubmit(data: { userId: number; taskId: number }): void {
    this.assignedTaskService.assignTaskToUser(data.taskId, data.userId).subscribe(() => {
      this.resetAssignmentPagination();
      this.loadAssignments();
    });
  }

  unassignTask(id: number): void {
    if (confirm('¿Desasignar esta tarea?')) {
      this.assignedTaskService.deleteAssignedTask(id).subscribe(() => {
        this.resetAssignmentPagination();
        this.loadAssignments();
      });
    }
  }

  updateCompletion(): void {
    if (this.completionForm.valid) {
      const { assignedTaskId } = this.completionForm.value;
      this.assignedTaskService.updateAssignedTaskCompletion(+assignedTaskId).subscribe(() => {
        this.completionForm.reset();
        this.resetAssignmentPagination();
        this.loadAssignments();
      });
    }
  }

  toggleCompletion(id: number): void {
    this.assignedTaskService.updateAssignedTaskCompletion(id).subscribe(() => {
      this.resetAssignmentPagination();
      this.loadAssignments();
    });
  }

  getUserEmail(id: number): string {
    return this.users.find(u => u.id === id)?.email ?? 'Desconocido';
  }

  getTaskTitle(taskId: number): string {
    return this.tasks.find(t => t.id === taskId)?.title ?? 'Desconocida';
  }

  @HostListener('window:scroll', [])
  onScroll(): void {
    const threshold = 50;
    const position = window.innerHeight + window.scrollY;
    const height = document.body.offsetHeight;

    if (position >= height - threshold && !this.isLoading) {
      this.loadAssignments();
    }
  }
}

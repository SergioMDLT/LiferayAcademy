import { Component, HostListener, OnInit } from '@angular/core';
import { ToastService } from '../../../shared/services/toast.service';
import { AuthService } from '../../../auth/services/auth.service';
import { AssignedTask } from '../../models/assigned-task';
import { AssignedTasksService } from '../../services/assigned-tasks.service';
import { filter, switchMap, take } from 'rxjs';

@Component({
  selector: 'tasks-main-page',
  standalone: false,
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent implements OnInit {
  public tasks: AssignedTask[] = [];
  public isLoading: boolean = false;
  public searchTerm: string = '';
  public totalPages: number = 0;
  public sortField: string = 'absolutePriority';

  private readonly searchKey = 'main-search-term';
  private readonly tasksKey = 'main-tasks';
  private readonly pageSize = 15;
  private currentPage = 0;
  private auth0Id!: string;

  constructor(
    private readonly authService: AuthService,
    private readonly taskService: AssignedTasksService,
    private readonly toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.authService.isAuthenticated$.pipe(
      filter(isAuth => isAuth),
      take(1),
      switchMap(() => this.authService.getUser()),
      filter((user): user is { sub: string } => !!user?.sub),
      take(1)
    ).subscribe(user => {
      this.auth0Id = user.sub;
      this.loadStateFromStorage(this.auth0Id);
    });
  }

  private isLocalStorageAvailable(): boolean {
    return typeof localStorage !== 'undefined';
  }

  private loadStateFromStorage(auth0Id: string): void {
    if (!this.isLocalStorageAvailable() || !auth0Id) {
      this.loadTasks();
      return;
    }

    const storedSearch = localStorage.getItem(`${this.searchKey}-${auth0Id}`);
    const storedTasks = localStorage.getItem(`${this.tasksKey}-${auth0Id}`);

    if (storedSearch) {
      this.searchTerm = storedSearch;
      if (storedTasks) {
        this.tasks = JSON.parse(storedTasks);
        return;
      }
      this.onSearch(storedSearch);
    } else {
      this.loadTasks();
    }
  }

  loadTasks(): void {
    if (this.currentPage >= this.totalPages && this.totalPages !== 0) return;
    this.isLoading = true;

    this.taskService.getAssignedTasks({
      completed: false,
      page: this.currentPage,
      size: this.pageSize,
      sort: this.sortField
    }).subscribe({
      next: tasks => {
        setTimeout(() => {
          this.tasks = [...this.tasks, ...tasks.content];
          this.currentPage++;
          this.totalPages = tasks.totalPages;
          this.saveTasksToStorage(this.auth0Id);
          this.isLoading = false;
        }, 500);
      },
      error: err => {
        console.error('Error loading tasks: ', err);
        this.toastService.showError('Error loading tasks');
        this.isLoading = false;
      }
    });
  }

  onSearch(term: string): void {
    this.searchTerm = term.trim();
    this.currentPage = 0;
    this.tasks = [];

    if (this.searchTerm.length === 0) {
      localStorage.removeItem(`${this.searchKey}-${this.auth0Id}`);
      localStorage.removeItem(`${this.tasksKey}-${this.auth0Id}`);
      this.loadTasks();
      return;
    }

    localStorage.setItem(`${this.searchKey}-${this.auth0Id}`, this.searchTerm);
    this.isLoading = true;

    this.taskService.getAssignedTasks({
      completed: false,
      title: this.searchTerm,
      page: this.currentPage,
      size: this.pageSize,
      sort: this.sortField
    }).subscribe({
      next: tasks => {
        setTimeout(() => {
          this.tasks = tasks.content;
          this.currentPage++;
          this.totalPages = tasks.totalPages;
          this.saveTasksToStorage(this.auth0Id);
          this.isLoading = false;
        }, 500);
      },
      error: err => {
        console.error('Error searching tasks: ', err);
        this.toastService.showError('Error searching tasks');
        this.isLoading = false;
      }
    });
  }

  private saveTasksToStorage(auth0Id: string): void {
    if (this.isLocalStorageAvailable()) {
      localStorage.setItem(`${this.tasksKey}-${auth0Id}`, JSON.stringify(this.tasks));
      localStorage.setItem(`lastUpdated-${auth0Id}`, Date.now().toString());
    }
  }

  removeCompletedTask(task: AssignedTask): void {
    const filteredTasks = this.tasks.filter(t => t.id !== task.id);
    const updatedTasks = filteredTasks.map((t, index) => ({
      ...t,
      absolutePriority: index + 1
    }));

    this.tasks = [...updatedTasks];

    const storedTasks = localStorage.getItem(`${this.tasksKey}-${this.auth0Id}`);
    if (storedTasks) {
      let parsedTasks = JSON.parse(storedTasks);
      parsedTasks = parsedTasks
        .filter((t: AssignedTask) => t.id !== task.id)
        .map((t: AssignedTask, index: number) => ({
          ...t,
          absolutePriority: index + 1
        }));

      localStorage.setItem(`${this.tasksKey}-${this.auth0Id}`, JSON.stringify(parsedTasks));
    }
  }

  @HostListener('window:scroll', [])
  onScroll(): void {
    const threshold = 50;
    const position = window.innerHeight + window.scrollY;
    const height = document.body.offsetHeight;

    if (position >= height - threshold && !this.isLoading) this.loadTasks();
  }

}

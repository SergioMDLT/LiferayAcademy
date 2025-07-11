import { Component, HostListener, OnInit } from '@angular/core';
import { ToastService } from '../../../shared/services/toast.service';
import { AuthService } from '../../../auth/services/auth.service';
import { AssignedTask } from '../../models/assigned-task';
import { AssignedTasksService } from '../../services/assigned-tasks.service';

@Component({
  selector: 'tasks-history-page',
  standalone: false,
  templateUrl: './history-page.component.html',
  styleUrl: './history-page.component.scss'
})
export class HistoryPageComponent implements OnInit {
  public tasks: AssignedTask[] = [];
  public isLoading: boolean = false;
  public searchTerm: string = '';
  public totalPages: number = 0;
  public sortField: string = 'id';

  private readonly searchKey = 'history-search-term';
  private readonly tasksKey = 'history-tasks';
  private readonly pageSize = 15;
  private currentPage = 0;

  constructor(
    private readonly authService: AuthService,
    private readonly taskService: AssignedTasksService,
    private readonly toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getAuthenticatedUserId(auth0Id => {
      this.loadStateFromStorage(auth0Id);
    });
  }

  private getAuthenticatedUserId(callback: (auth0Id: string) => void): void {
    this.authService.getUser().subscribe(user => {
      const auth0Id = user?.sub;
      if (!auth0Id) {
        console.warn('⚠️ No Auth0 ID found');
        return;
      }
      callback(auth0Id);
    });
  }

  private isLocalStorageAvailable(): boolean {
    return typeof localStorage !== 'undefined';
  }

  private loadStateFromStorage(auth0Id: string): void {
    if (!this.isLocalStorageAvailable()) {
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

    this.getAuthenticatedUserId(auth0Id => {
      if (!auth0Id) {
        console.warn('⚠️ No Auth0 ID found. Skipping task loading');
        this.isLoading = false;
        return;
      }

      this.taskService.getAssignedTasks({
        page: this.currentPage,
        size: this.pageSize,
        sort: this.sortField
      }).subscribe({
        next: tasks => {
          setTimeout(() => {
            this.tasks = [...this.tasks, ...tasks.content];
            this.currentPage++;
            this.totalPages = tasks.totalPages;
            this.saveTasksToStorage(auth0Id);
            this.isLoading = false;
          }, 500);
        },
        error: err => {
          console.error('Error loading tasks: ', err);
          this.toastService.showError('Error loading tasks');
          this.isLoading = false;
        }
      });
    });
  }

  onSearch(term: string): void {
    this.getAuthenticatedUserId(auth0Id => {
      this.searchTerm = term.trim();
      this.currentPage = 0;
      this.tasks = [];

      if (this.searchTerm.length === 0) {
        localStorage.removeItem(`${this.searchKey}-${auth0Id}`);
        localStorage.removeItem(`${this.tasksKey}-${auth0Id}`);
        this.loadTasks();
        return;
      }

      localStorage.setItem(`${this.searchKey}-${auth0Id}`, this.searchTerm);
      this.isLoading = true;

      this.taskService.getAssignedTasks({
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
            this.saveTasksToStorage(auth0Id);
            this.isLoading = false;
          }, 500);
        },
        error: err => {
          console.error('Error searching tasks: ', err);
          this.toastService.showError('Error searching tasks');
          this.isLoading = false;
        }
      });
    });
  }

  private saveTasksToStorage(auth0Id: string): void {
    if (this.isLocalStorageAvailable()) {
      localStorage.setItem(`${this.tasksKey}-${auth0Id}`, JSON.stringify(this.tasks));
      localStorage.setItem(`lastUpdated-${auth0Id}`, Date.now().toString());
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

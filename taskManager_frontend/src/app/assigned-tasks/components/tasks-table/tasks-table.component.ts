import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ToastService } from '../../../shared/services/toast.service';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { forkJoin } from 'rxjs';
import { AssignedTasksService } from '../../../assigned-tasks/services/assigned-tasks.service';
import { AssignedTask } from '../../../assigned-tasks/models/assigned-task';

@Component({
  selector: 'tasks-table',
  standalone: false,
  templateUrl: './tasks-table.component.html',
  styleUrl: './tasks-table.component.scss'
})
export class TasksTableComponent {
  @Input() tasks: AssignedTask[] = [];
  @Input() showPriority: boolean = false;
  @Input() priorityType: 'module' | 'absolute' = 'module';

  @Output() taskCompleted: EventEmitter<AssignedTask> = new EventEmitter<AssignedTask>();

  constructor (
    private readonly taskService: AssignedTasksService,
    private readonly toastService: ToastService
  ) {}

  getPriority(task: AssignedTask): number | null {
    return this.priorityType === 'absolute' ? task.absolutePriority : task.modulePriority;
  }

  onUpdateTask(id: number): void {
    this.taskService.updateAssignedTaskCompletion(id).subscribe({
      next: (updatedTask) => {
        this.toastService.showSuccess(`Tarea con id ${id} actualizada`);
        const index = this.tasks.findIndex(task => task.id === updatedTask.id);
        if (index !== -1) this.tasks[index] = updatedTask;

        if (updatedTask.completed) {
          this.taskCompleted.emit(updatedTask);
        }
      },
      error: () => this.toastService.showError('Error actualizando tarea')
    });
  }

  onDeleteTask(id: number): void {
    if (!confirm('¿Eliminar tarea definitivamente?')) return;

    const deletedTask = this.tasks.find(task => task.id === id);
    const removedPriority = deletedTask ? this.getPriority(deletedTask) : null;

    this.taskService.deleteAssignedTask(id).subscribe({
      next: () => {
        this.toastService.showSuccess(`Tarea con id ${id} eliminada`);
        this.tasks = this.tasks
          .filter(task => task.id !== id)
          .map(task => {
            const current = this.getPriority(task);
            return {
              ...task,
              [this.getPriorityKey()]: current != null && removedPriority != null && current > removedPriority
                ? current - 1
                : current
            };
          });
      },
      error: () => this.toastService.showError('Error eliminando tarea')
    });
  }

  private adjustPrioritiesAfterCompletion(task: AssignedTask): void {
    const removedPriority = this.getPriority(task);

    this.tasks.forEach(t => {
      const current = this.getPriority(t);
      if (current != null && removedPriority != null && current > removedPriority) {
        t[this.getPriorityKey()] = current - 1;
      }
    });

    this.tasks = this.tasks.filter(t => t.id !== task.id);
    this.taskCompleted.emit(task);
  }

  onDrop(event: CdkDragDrop<AssignedTask[]>): void {
    if (event.previousIndex === event.currentIndex) return;
    moveItemInArray(this.tasks, event.previousIndex, event.currentIndex);

    const updatedTasks = this.tasks.map((task, index) => {
      const updated = { ...task };
      if (this.priorityType === 'absolute') {
        updated.absolutePriority = index + 1;
      } else {
        updated.modulePriority = index + 1;
      }
      return updated;
    });

    const updateRequests = updatedTasks.map(task => {
      return this.priorityType === 'absolute'
        ? this.taskService.updateAssignedTaskAbsolutePriority(task.id, task.absolutePriority!)
        : this.taskService.updateAssignedTaskModulePriority(task.id, task.modulePriority!);
    });

    forkJoin(updateRequests).subscribe({
      next: () => {
        this.toastService.showSuccess("Prioridades actualizadas");
        this.tasks = updatedTasks;
      },
      error: () => {
        this.toastService.showError("No se pudieron actualizar las prioridades");
        moveItemInArray(this.tasks, event.currentIndex, event.previousIndex);
      }
    });
  }

  private getPriorityKey(): 'modulePriority' | 'absolutePriority' {
    return this.priorityType === 'absolute' ? 'absolutePriority' : 'modulePriority';
  }

}

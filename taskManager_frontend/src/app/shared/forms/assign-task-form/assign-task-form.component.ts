import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task } from '../../../tasks/models/task';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../users/models/user';

@Component({
  selector: 'app-assign-task-form',
  standalone: false,
  templateUrl: './assign-task-form.component.html',
  styleUrl: './assign-task-form.component.scss'
})
export class AssignTaskFormComponent {
  @Input() users: User[] = [];
  @Input() tasks: Task[] = [];
  @Output() formSubmit = new EventEmitter<{ userId: number; taskId: number }>();

  assignForm: FormGroup;

  constructor(private readonly fb: FormBuilder) {
    this.assignForm = this.fb.group({
      userId: [null, Validators.required],
      taskId: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.assignForm.valid) {
      this.formSubmit.emit(this.assignForm.value);
      this.assignForm.reset();
    }
  }
}

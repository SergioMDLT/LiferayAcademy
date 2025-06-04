import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../users/models/user';

@Component({
  selector: 'app-assign-module-form',
  standalone: false,
  templateUrl: './assign-module-form.component.html',
  styleUrl: './assign-module-form.component.scss'
})
export class AssignModuleFormComponent {
  @Input() users: User[] = [];
  @Input() modules: TrainingModule[] = [];
  @Output() formSubmit = new EventEmitter<{ userId: number; trainingModuleId: number }>();

  assignModuleForm: FormGroup;

  constructor(private readonly fb: FormBuilder) {
    this.assignModuleForm = this.fb.group({
      userId: [null, Validators.required],
      trainingModuleId: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.assignModuleForm.valid) {
      this.formSubmit.emit(this.assignModuleForm.value);
      this.assignModuleForm.reset();
    }
  }

}

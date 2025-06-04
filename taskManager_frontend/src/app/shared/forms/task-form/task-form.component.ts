import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';

@Component({
  selector: 'shared-task-form',
  standalone: false,
  templateUrl: './task-form.component.html',
  styleUrl: './task-form.component.scss'
})
export class TaskFormComponent {

  @Input() trainingModules: TrainingModule[] = [];
  @Output() formSubmit = new EventEmitter<{ title: string; description: string; trainingModuleId: number }>();

  taskForm: FormGroup;

  constructor( private readonly formBuilder: FormBuilder ) {
    this.taskForm = this.formBuilder.group({
      title: [ '', Validators.required ],
      description: [ '' ],
      trainingModuleId: [ null, Validators.required ]
    });
  }

  onSubmit() {
    if ( this.taskForm.valid ) {
      this.formSubmit.emit(this.taskForm.value);
      this.taskForm.reset();
    }
  }

}

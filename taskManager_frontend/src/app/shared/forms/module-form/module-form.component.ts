import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TrainingModule } from '../../../training-modules/models/TrainingModule';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-module-form',
  standalone: false,
  templateUrl: './module-form.component.html',
  styleUrl: './module-form.component.scss'
})
export class ModuleFormComponent implements OnInit {
  @Input() initialData: Partial<TrainingModule> | null = null;
  @Output() formSubmit = new EventEmitter<{ name: string; description: string }>();

  moduleForm: FormGroup;

  constructor(private readonly fb: FormBuilder) {
    this.moduleForm = this.fb.group({
      name: ['', Validators.required],
      description: ['']
    });
  }

  ngOnInit(): void {
    if (this.initialData) {
      this.moduleForm.patchValue(this.initialData);
    }
  }

  onSubmit(): void {
    if (this.moduleForm.valid) {
      this.formSubmit.emit(this.moduleForm.value);
      this.moduleForm.reset();
    }
  }

}

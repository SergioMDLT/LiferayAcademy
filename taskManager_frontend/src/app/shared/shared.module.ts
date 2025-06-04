import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SearchboxComponent } from './components/searchbox/searchbox.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';;
import { SpinnerComponent } from './components/spinner/spinner.component';
import { TaskFormComponent } from './forms/task-form/task-form.component';
import { ModuleFormComponent } from './forms/module-form/module-form.component';
import { AssignTaskFormComponent } from './forms/assign-task-form/assign-task-form.component';
import { AssignModuleFormComponent } from './forms/assign-module-form/assign-module-form.component';
import { CliPageComponent } from './components/cli-page/cli-page.component';


@NgModule({
  declarations: [
    SearchboxComponent,
    SidebarComponent,
    SpinnerComponent,
    TaskFormComponent,
    ModuleFormComponent,
    AssignTaskFormComponent,
    AssignModuleFormComponent,
    CliPageComponent
  ],
  exports: [
    MatSnackBarModule,
    SearchboxComponent,
    SidebarComponent,
    SpinnerComponent,
    TaskFormComponent,
    ModuleFormComponent,
    AssignTaskFormComponent,
    AssignModuleFormComponent,
  ],
  imports: [
    CommonModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  providers: [],
})
export class SharedModule { }

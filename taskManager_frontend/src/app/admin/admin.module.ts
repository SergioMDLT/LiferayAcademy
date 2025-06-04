import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPanelPageComponent } from './pages/admin-panel-page/admin-panel-page.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { AdminModulesPageComponent } from './pages/admin-modules-page/admin-modules-page.component';
import { AdminAssignmentsPageComponent } from './pages/admin-assignments-page/admin-assignments-page.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminTaskAssignmentsPageComponent } from './pages/admin-task-assignments-page/admin-task-assignments-page.component';
import { AdminTasksPageComponent } from './pages/admin-tasks-page/admin-tasks-page.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    AdminPanelPageComponent,
    AdminModulesPageComponent,
    AdminAssignmentsPageComponent,
    AdminTaskAssignmentsPageComponent,
    AdminTasksPageComponent
  ],
  imports: [
    AdminRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    MatButtonModule,
    MatIconModule,
  ],
})
export class AdminModule {}

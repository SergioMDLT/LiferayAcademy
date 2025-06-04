import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatIconModule } from '@angular/material/icon';
import { SharedModule } from '../shared/shared.module';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { HistoryPageComponent } from './pages/history-page/history-page.component';
import { AssignedModulesPageComponent } from './pages/assigned-modules-page/assigned-modules-page.component';
import { TasksByModulePageComponent } from './pages/tasks-by-module-page/tasks-by-module-page.component';
import { TrainingModulesPageComponent } from './pages/training-modules-page/training-modules-page.component';
import { TasksModule } from '../tasks/tasks.module';
import { TasksTableComponent } from './components/tasks-table/tasks-table.component';

@NgModule({
  declarations: [
    MainPageComponent,
    HistoryPageComponent,
    AssignedModulesPageComponent,
    TasksByModulePageComponent,
    TrainingModulesPageComponent,
    TasksTableComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    DragDropModule,
    MatIconModule,
    SharedModule,
    TasksModule
  ],
  exports: [
    MainPageComponent,
    HistoryPageComponent,
    AssignedModulesPageComponent,
    TasksByModulePageComponent,
    TrainingModulesPageComponent,
    TasksTableComponent
  ]
})
export class AssignedTasksModule {}

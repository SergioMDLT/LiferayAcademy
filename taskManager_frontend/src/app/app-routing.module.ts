import { CallbackComponent } from './auth/callback/callback.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './assigned-tasks/pages/main-page/main-page.component';
import { AuthGuard } from './auth/services/auth.guard';
import { TrainingModulesPageComponent } from './assigned-tasks/pages/training-modules-page/training-modules-page.component';
import { AssignedModulesPageComponent } from './assigned-tasks/pages/assigned-modules-page/assigned-modules-page.component';
import { TasksByModulePageComponent } from './assigned-tasks/pages/tasks-by-module-page/tasks-by-module-page.component';
import { HistoryPageComponent } from './assigned-tasks/pages/history-page/history-page.component';
import { CliPageComponent } from './shared/components/cli-page/cli-page.component';
import { AdminGuard } from './auth/services/admin.guard';

const routes: Routes = [
  { path: 'callback', component: CallbackComponent },
  // Assigned task routes
  { path: 'main', component: MainPageComponent, canActivate: [AuthGuard] },
  { path: 'history', component: HistoryPageComponent, canActivate: [AuthGuard] },
  { path: 'assigned-modules', component: AssignedModulesPageComponent, canActivate: [AuthGuard] },
  { path: 'tasks-by-module/:id', component: TasksByModulePageComponent, canActivate: [AuthGuard] },
  { path: 'cli', component: CliPageComponent, canActivate: [AuthGuard] },
  // Training modules
  { path: 'modules', component: TrainingModulesPageComponent, canActivate: [AuthGuard] },
  // Admin
  {
    path: 'admin-panel',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard, AdminGuard]
  },
  // Default
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: '**', redirectTo: 'main' }
];

@NgModule({
  imports: [
    RouterModule.forRoot( routes )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }

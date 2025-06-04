
import { RouterModule, Routes } from "@angular/router";
import { AdminPanelPageComponent } from "./pages/admin-panel-page/admin-panel-page.component";
import { AdminModulesPageComponent } from "./pages/admin-modules-page/admin-modules-page.component";
import { AdminAssignmentsPageComponent } from "./pages/admin-assignments-page/admin-assignments-page.component";
import { NgModule } from "@angular/core";
import { AdminTasksPageComponent } from "./pages/admin-tasks-page/admin-tasks-page.component";
import { AdminTaskAssignmentsPageComponent } from "./pages/admin-task-assignments-page/admin-task-assignments-page.component";

const routes: Routes = [
  {
    path: '',
    component: AdminPanelPageComponent,
    children: [
      { path: 'manage-modules', component: AdminModulesPageComponent },
      { path: 'assignments', component: AdminAssignmentsPageComponent },
      { path: 'manage-tasks', component: AdminTasksPageComponent },
      { path: 'task-assignments', component: AdminTaskAssignmentsPageComponent },
      { path: '', redirectTo: 'manage-modules', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}

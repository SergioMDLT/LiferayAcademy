import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { SharedModule } from './shared/shared.module';
import { TasksModule } from './tasks/tasks.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { authConfig } from './auth.config';
import { AuthInterceptor } from './auth/interceptors/auth.interceptor';
import { AuthModule } from '@auth0/auth0-angular';
import { AuthModule as LocalAuthModule } from './auth/auth.module';
import { AdminModule } from './admin/admin.module';
import { AssignedTasksModule } from './assigned-tasks/assigned-tasks.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    DragDropModule,
    MatSnackBarModule,
    AppRoutingModule,
    SharedModule,
    LocalAuthModule,
    AuthModule.forRoot(authConfig),
    AdminModule,
    TasksModule,
    AssignedTasksModule
  ],
  providers: [
    provideAnimationsAsync(),
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

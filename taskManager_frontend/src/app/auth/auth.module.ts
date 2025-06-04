import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CallbackComponent } from './callback/callback.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [CallbackComponent],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [CallbackComponent]
})
export class AuthModule {}

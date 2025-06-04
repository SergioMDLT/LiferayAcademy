import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksByModulePageComponent } from './tasks-by-module-page.component';

describe('TasksByModulePageComponent', () => {
  let component: TasksByModulePageComponent;
  let fixture: ComponentFixture<TasksByModulePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TasksByModulePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TasksByModulePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

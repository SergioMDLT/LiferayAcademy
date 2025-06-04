import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignModuleFormComponent } from './assign-module-form.component';

describe('AssignModuleFormComponent', () => {
  let component: AssignModuleFormComponent;
  let fixture: ComponentFixture<AssignModuleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignModuleFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignModuleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

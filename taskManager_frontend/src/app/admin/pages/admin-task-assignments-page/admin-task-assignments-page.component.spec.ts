import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTaskAssignmentsPageComponent } from './admin-task-assignments-page.component';

describe('AdminTaskAssignmentsPageComponent', () => {
  let component: AdminTaskAssignmentsPageComponent;
  let fixture: ComponentFixture<AdminTaskAssignmentsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminTaskAssignmentsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTaskAssignmentsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

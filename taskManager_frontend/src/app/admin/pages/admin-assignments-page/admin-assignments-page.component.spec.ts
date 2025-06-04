import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAssignmentsPageComponent } from './admin-assignments-page.component';

describe('AdminAssignmentsPageComponent', () => {
  let component: AdminAssignmentsPageComponent;
  let fixture: ComponentFixture<AdminAssignmentsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAssignmentsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAssignmentsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

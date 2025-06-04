import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTasksPageComponent } from './admin-tasks-page.component';

describe('AdminTasksPageComponent', () => {
  let component: AdminTasksPageComponent;
  let fixture: ComponentFixture<AdminTasksPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminTasksPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTasksPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

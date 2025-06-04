import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminModulesPageComponent } from './admin-modules-page.component';

describe('AdminModulesPageComponent', () => {
  let component: AdminModulesPageComponent;
  let fixture: ComponentFixture<AdminModulesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminModulesPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminModulesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

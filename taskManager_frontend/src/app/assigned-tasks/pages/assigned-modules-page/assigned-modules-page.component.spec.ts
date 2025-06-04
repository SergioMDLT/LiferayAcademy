import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedModulesPageComponent } from './assigned-modules-page.component';

describe('AssignedModulesPageComponent', () => {
  let component: AssignedModulesPageComponent;
  let fixture: ComponentFixture<AssignedModulesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignedModulesPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignedModulesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

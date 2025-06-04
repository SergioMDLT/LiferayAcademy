import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingModulesPageComponent } from './training-modules-page.component';

describe('TrainingModulesPageComponent', () => {
  let component: TrainingModulesPageComponent;
  let fixture: ComponentFixture<TrainingModulesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TrainingModulesPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainingModulesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

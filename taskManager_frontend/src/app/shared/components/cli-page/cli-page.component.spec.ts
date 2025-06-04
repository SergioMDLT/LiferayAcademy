import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CliPageComponent } from './cli-page.component';

describe('CliPageComponent', () => {
  let component: CliPageComponent;
  let fixture: ComponentFixture<CliPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CliPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CliPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

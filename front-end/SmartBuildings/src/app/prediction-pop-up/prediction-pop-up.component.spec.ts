import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionPopUpComponent } from './prediction-pop-up.component';

describe('PredictionPopUpComponent', () => {
  let component: PredictionPopUpComponent;
  let fixture: ComponentFixture<PredictionPopUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredictionPopUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

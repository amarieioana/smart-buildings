import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportPopUpComponent } from './import-pop-up.component';

describe('ImportPopUpComponent', () => {
  let component: ImportPopUpComponent;
  let fixture: ComponentFixture<ImportPopUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImportPopUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthBoatPageComponent } from './unauth-boat-page.component';

describe('UnauthBoatPageComponent', () => {
  let component: UnauthBoatPageComponent;
  let fixture: ComponentFixture<UnauthBoatPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthBoatPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthBoatPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

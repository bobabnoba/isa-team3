import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatPageComponent } from './boat-page.component';

describe('BoatPageComponent', () => {
  let component: BoatPageComponent;
  let fixture: ComponentFixture<BoatPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

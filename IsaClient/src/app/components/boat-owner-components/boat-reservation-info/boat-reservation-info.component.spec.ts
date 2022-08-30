import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationInfoComponent } from './boat-reservation-info.component';

describe('BoatReservationInfoComponent', () => {
  let component: BoatReservationInfoComponent;
  let fixture: ComponentFixture<BoatReservationInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReservationInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

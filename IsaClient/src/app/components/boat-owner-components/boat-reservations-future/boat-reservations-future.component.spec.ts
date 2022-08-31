import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationsFutureComponent } from './boat-reservations-future.component';

describe('BoatReservationsFutureComponent', () => {
  let component: BoatReservationsFutureComponent;
  let fixture: ComponentFixture<BoatReservationsFutureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReservationsFutureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationsFutureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

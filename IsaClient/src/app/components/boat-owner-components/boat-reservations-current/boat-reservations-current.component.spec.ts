import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationsCurrentComponent } from './boat-reservations-current.component';

describe('BoatReservationsCurrentComponent', () => {
  let component: BoatReservationsCurrentComponent;
  let fixture: ComponentFixture<BoatReservationsCurrentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReservationsCurrentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationsCurrentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

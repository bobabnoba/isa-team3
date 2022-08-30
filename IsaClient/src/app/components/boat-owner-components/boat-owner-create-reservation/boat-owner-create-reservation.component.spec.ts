import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerCreateReservationComponent } from './boat-owner-create-reservation.component';

describe('BoatOwnerCreateReservationComponent', () => {
  let component: BoatOwnerCreateReservationComponent;
  let fixture: ComponentFixture<BoatOwnerCreateReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerCreateReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerCreateReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

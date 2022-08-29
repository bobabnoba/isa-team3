import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationHistoryHomesComponent } from './reservation-history-homes.component';

describe('ReservationHistoryHomesComponent', () => {
  let component: ReservationHistoryHomesComponent;
  let fixture: ComponentFixture<ReservationHistoryHomesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationHistoryHomesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationHistoryHomesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

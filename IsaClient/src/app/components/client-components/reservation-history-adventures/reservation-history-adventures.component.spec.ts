import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationHistoryAdventuresComponent } from './reservation-history-adventures.component';

describe('ReservationHistoryAdventuresComponent', () => {
  let component: ReservationHistoryAdventuresComponent;
  let fixture: ComponentFixture<ReservationHistoryAdventuresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationHistoryAdventuresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationHistoryAdventuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

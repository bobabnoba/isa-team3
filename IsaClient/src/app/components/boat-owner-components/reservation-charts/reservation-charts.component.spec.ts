import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationChartsComponent } from './reservation-charts.component';

describe('ReservationChartsComponent', () => {
  let component: ReservationChartsComponent;
  let fixture: ComponentFixture<ReservationChartsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationChartsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationChartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

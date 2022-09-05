import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeReservationChartComponent } from './home-reservation-chart.component';

describe('HomeReservationChartComponent', () => {
  let component: HomeReservationChartComponent;
  let fixture: ComponentFixture<HomeReservationChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeReservationChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeReservationChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureReservationChartsComponent } from './adventure-reservation-charts.component';

describe('AdventureReservationChartsComponent', () => {
  let component: AdventureReservationChartsComponent;
  let fixture: ComponentFixture<AdventureReservationChartsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureReservationChartsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureReservationChartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

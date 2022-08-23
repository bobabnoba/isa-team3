import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationReportsComponent } from './reservation-reports.component';

describe('ReservationReportsComponent', () => {
  let component: ReservationReportsComponent;
  let fixture: ComponentFixture<ReservationReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

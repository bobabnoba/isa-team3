import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsUpcomingComponent } from './reservations-upcoming.component';

describe('ReservationsUpcomingComponent', () => {
  let component: ReservationsUpcomingComponent;
  let fixture: ComponentFixture<ReservationsUpcomingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationsUpcomingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationsUpcomingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

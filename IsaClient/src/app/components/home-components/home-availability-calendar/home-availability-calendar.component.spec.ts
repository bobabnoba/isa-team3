import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeAvailabilityCalendarComponent } from './home-availability-calendar.component';

describe('HomeAvailabilityCalendarComponent', () => {
  let component: HomeAvailabilityCalendarComponent;
  let fixture: ComponentFixture<HomeAvailabilityCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeAvailabilityCalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeAvailabilityCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

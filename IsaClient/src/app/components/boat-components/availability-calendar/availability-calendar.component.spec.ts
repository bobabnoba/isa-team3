import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailabilityCalendarComponent } from './availability-calendar.component';

describe('AvailabilityCalendarComponent', () => {
  let component: AvailabilityCalendarComponent;
  let fixture: ComponentFixture<AvailabilityCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailabilityCalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailabilityCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailabilityCalendarBoatOwnerComponent } from './availability-calendar-boat-owner.component';

describe('AvailabilityCalendarBoatOwnerComponent', () => {
  let component: AvailabilityCalendarBoatOwnerComponent;
  let fixture: ComponentFixture<AvailabilityCalendarBoatOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailabilityCalendarBoatOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailabilityCalendarBoatOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorAvailabilityComponent } from './instructor-availability.component';

describe('InstructorAvailabilityComponent', () => {
  let component: InstructorAvailabilityComponent;
  let fixture: ComponentFixture<InstructorAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

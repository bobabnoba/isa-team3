import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorCreateReservationComponent } from './instructor-create-reservation.component';

describe('InstructorCreateReservationComponent', () => {
  let component: InstructorCreateReservationComponent;
  let fixture: ComponentFixture<InstructorCreateReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorCreateReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorCreateReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

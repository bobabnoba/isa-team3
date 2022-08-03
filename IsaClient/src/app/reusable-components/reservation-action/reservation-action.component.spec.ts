import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationActionComponent } from './reservation-action.component';

describe('ReservationActionComponent', () => {
  let component: ReservationActionComponent;
  let fixture: ComponentFixture<ReservationActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationActionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

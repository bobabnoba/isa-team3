import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationHistoryBoatsComponent } from './reservation-history-boats.component';

describe('ReservationHistoryBoatsComponent', () => {
  let component: ReservationHistoryBoatsComponent;
  let fixture: ComponentFixture<ReservationHistoryBoatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationHistoryBoatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationHistoryBoatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

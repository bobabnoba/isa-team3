import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationsHistoryComponent } from './boat-reservations-history.component';

describe('BoatReservationsHistoryComponent', () => {
  let component: BoatReservationsHistoryComponent;
  let fixture: ComponentFixture<BoatReservationsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReservationsHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

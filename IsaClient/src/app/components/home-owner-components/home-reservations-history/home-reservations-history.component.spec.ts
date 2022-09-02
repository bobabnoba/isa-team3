import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeReservationsHistoryComponent } from './home-reservations-history.component';

describe('HomeReservationsHistoryComponent', () => {
  let component: HomeReservationsHistoryComponent;
  let fixture: ComponentFixture<HomeReservationsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeReservationsHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeReservationsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

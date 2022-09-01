import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeReservationsFutureComponent } from './home-reservations-future.component';

describe('HomeReservationsFutureComponent', () => {
  let component: HomeReservationsFutureComponent;
  let fixture: ComponentFixture<HomeReservationsFutureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeReservationsFutureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeReservationsFutureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

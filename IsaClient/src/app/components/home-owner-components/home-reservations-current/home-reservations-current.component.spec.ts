import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeReservationsCurrentComponent } from './home-reservations-current.component';

describe('HomeReservationsCurrentComponent', () => {
  let component: HomeReservationsCurrentComponent;
  let fixture: ComponentFixture<HomeReservationsCurrentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeReservationsCurrentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeReservationsCurrentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

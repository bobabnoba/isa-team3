import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeReservationInfoComponent } from './home-reservation-info.component';

describe('HomeReservationInfoComponent', () => {
  let component: HomeReservationInfoComponent;
  let fixture: ComponentFixture<HomeReservationInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeReservationInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeReservationInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

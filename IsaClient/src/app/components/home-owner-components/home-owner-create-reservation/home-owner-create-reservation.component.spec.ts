import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerCreateReservationComponent } from './home-owner-create-reservation.component';

describe('HomeOwnerCreateReservationComponent', () => {
  let component: HomeOwnerCreateReservationComponent;
  let fixture: ComponentFixture<HomeOwnerCreateReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerCreateReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerCreateReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

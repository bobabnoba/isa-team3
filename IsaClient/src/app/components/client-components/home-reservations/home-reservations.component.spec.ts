import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeReservationsComponent } from './home-reservations.component';

describe('HomeReservationsComponent', () => {
  let component: HomeReservationsComponent;
  let fixture: ComponentFixture<HomeReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

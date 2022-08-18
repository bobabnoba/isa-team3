import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseCardReservationComponent } from './browse-card-reservation.component';

describe('BrowseCardReservationComponent', () => {
  let component: BrowseCardReservationComponent;
  let fixture: ComponentFixture<BrowseCardReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseCardReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseCardReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

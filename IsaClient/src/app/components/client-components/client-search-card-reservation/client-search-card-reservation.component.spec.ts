import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSearchCardReservationComponent } from './client-search-card-reservation.component';

describe('ClientSearchCardReservationComponent', () => {
  let component: ClientSearchCardReservationComponent;
  let fixture: ComponentFixture<ClientSearchCardReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientSearchCardReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientSearchCardReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

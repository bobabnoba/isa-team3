import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientReservationsHistoryComponent } from './client-reservations-history.component';

describe('ClientReservationsHistoryComponent', () => {
  let component: ClientReservationsHistoryComponent;
  let fixture: ComponentFixture<ClientReservationsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientReservationsHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientReservationsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

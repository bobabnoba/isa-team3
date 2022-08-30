import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationPreviewComponent } from './boat-reservation-preview.component';

describe('BoatReservationPreviewComponent', () => {
  let component: BoatReservationPreviewComponent;
  let fixture: ComponentFixture<BoatReservationPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReservationPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerAvailabilityComponent } from './boat-owner-availability.component';

describe('BoatOwnerAvailabilityComponent', () => {
  let component: BoatOwnerAvailabilityComponent;
  let fixture: ComponentFixture<BoatOwnerAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

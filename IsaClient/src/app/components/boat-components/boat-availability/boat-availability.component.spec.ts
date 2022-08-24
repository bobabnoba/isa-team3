import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatAvailabilityComponent } from './boat-availability.component';

describe('BoatAvailabilityComponent', () => {
  let component: BoatAvailabilityComponent;
  let fixture: ComponentFixture<BoatAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

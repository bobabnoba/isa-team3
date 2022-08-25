import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatSpecialOfferComponent } from './boat-special-offer.component';

describe('BoatSpecialOfferComponent', () => {
  let component: BoatSpecialOfferComponent;
  let fixture: ComponentFixture<BoatSpecialOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatSpecialOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatSpecialOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

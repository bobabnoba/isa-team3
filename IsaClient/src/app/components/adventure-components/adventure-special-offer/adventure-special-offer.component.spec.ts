import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureSpecialOfferComponent } from './adventure-special-offer.component';

describe('AdventureSpecialOfferComponent', () => {
  let component: AdventureSpecialOfferComponent;
  let fixture: ComponentFixture<AdventureSpecialOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureSpecialOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureSpecialOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

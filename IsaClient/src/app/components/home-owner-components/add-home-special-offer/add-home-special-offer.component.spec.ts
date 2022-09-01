import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHomeSpecialOfferComponent } from './add-home-special-offer.component';

describe('AddHomeSpecialOfferComponent', () => {
  let component: AddHomeSpecialOfferComponent;
  let fixture: ComponentFixture<AddHomeSpecialOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddHomeSpecialOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddHomeSpecialOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

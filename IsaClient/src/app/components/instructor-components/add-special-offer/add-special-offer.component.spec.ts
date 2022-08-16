import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSpecialOfferComponent } from './add-special-offer.component';

describe('AddSpecialOfferComponent', () => {
  let component: AddSpecialOfferComponent;
  let fixture: ComponentFixture<AddSpecialOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSpecialOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSpecialOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

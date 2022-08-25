import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBoatSpecialOfferComponent } from './add-boat-special-offer.component';

describe('AddBoatSpecialOfferComponent', () => {
  let component: AddBoatSpecialOfferComponent;
  let fixture: ComponentFixture<AddBoatSpecialOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddBoatSpecialOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBoatSpecialOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

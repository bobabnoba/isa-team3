import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertiserEarningsComponent } from './advertiser-earnings.component';

describe('AdvertiserEarningsComponent', () => {
  let component: AdvertiserEarningsComponent;
  let fixture: ComponentFixture<AdvertiserEarningsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvertiserEarningsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvertiserEarningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerBoatProfileComponent } from './boat-owner-boat-profile.component';

describe('BoatOwnerBoatProfileComponent', () => {
  let component: BoatOwnerBoatProfileComponent;
  let fixture: ComponentFixture<BoatOwnerBoatProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerBoatProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerBoatProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

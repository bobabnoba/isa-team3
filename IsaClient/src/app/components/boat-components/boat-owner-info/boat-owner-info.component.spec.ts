import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerInfoComponent } from './boat-owner-info.component';

describe('BoatOwnerInfoComponent', () => {
  let component: BoatOwnerInfoComponent;
  let fixture: ComponentFixture<BoatOwnerInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerTopbarComponent } from './boat-owner-topbar.component';

describe('BoatOwnerTopbarComponent', () => {
  let component: BoatOwnerTopbarComponent;
  let fixture: ComponentFixture<BoatOwnerTopbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerTopbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerTopbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

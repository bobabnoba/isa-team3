import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerDashboardComponent } from './boat-owner-dashboard.component';

describe('BoatOwnerDashboardComponent', () => {
  let component: BoatOwnerDashboardComponent;
  let fixture: ComponentFixture<BoatOwnerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

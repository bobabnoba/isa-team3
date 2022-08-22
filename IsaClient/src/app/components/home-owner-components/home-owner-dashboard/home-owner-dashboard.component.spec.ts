import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerDashboardComponent } from './home-owner-dashboard.component';

describe('HomeOwnerDashboardComponent', () => {
  let component: HomeOwnerDashboardComponent;
  let fixture: ComponentFixture<HomeOwnerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

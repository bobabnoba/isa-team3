import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeAvailabilityComponent } from './home-availability.component';

describe('HomeAvailabilityComponent', () => {
  let component: HomeAvailabilityComponent;
  let fixture: ComponentFixture<HomeAvailabilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeAvailabilityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

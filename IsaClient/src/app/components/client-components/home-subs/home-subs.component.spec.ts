import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSubsComponent } from './home-subs.component';

describe('HomeSubsComponent', () => {
  let component: HomeSubsComponent;
  let fixture: ComponentFixture<HomeSubsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeSubsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeSubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

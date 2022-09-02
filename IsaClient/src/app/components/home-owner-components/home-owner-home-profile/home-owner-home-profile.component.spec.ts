import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerHomeProfileComponent } from './home-owner-home-profile.component';

describe('HomeOwnerHomeProfileComponent', () => {
  let component: HomeOwnerHomeProfileComponent;
  let fixture: ComponentFixture<HomeOwnerHomeProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerHomeProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerHomeProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

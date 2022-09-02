import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerInfoComponent } from './home-owner-info.component';

describe('HomeOwnerInfoComponent', () => {
  let component: HomeOwnerInfoComponent;
  let fixture: ComponentFixture<HomeOwnerInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

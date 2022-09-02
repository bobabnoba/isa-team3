import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerTopbarComponent } from './home-owner-topbar.component';

describe('HomeOwnerTopbarComponent', () => {
  let component: HomeOwnerTopbarComponent;
  let fixture: ComponentFixture<HomeOwnerTopbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerTopbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerTopbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerSidebarComponent } from './home-owner-sidebar.component';

describe('HomeOwnerSidebarComponent', () => {
  let component: HomeOwnerSidebarComponent;
  let fixture: ComponentFixture<HomeOwnerSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerSidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerSidebarComponent } from './boat-owner-sidebar.component';

describe('BoatOwnerSidebarComponent', () => {
  let component: BoatOwnerSidebarComponent;
  let fixture: ComponentFixture<BoatOwnerSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerSidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

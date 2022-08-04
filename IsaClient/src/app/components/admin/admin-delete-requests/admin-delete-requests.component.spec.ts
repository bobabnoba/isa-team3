import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDeleteRequestsComponent } from './admin-delete-requests.component';

describe('AdminDeleteRequestsComponent', () => {
  let component: AdminDeleteRequestsComponent;
  let fixture: ComponentFixture<AdminDeleteRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminDeleteRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDeleteRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

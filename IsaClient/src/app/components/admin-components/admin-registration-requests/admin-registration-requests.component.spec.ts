import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRegistrationRequestsComponent } from './admin-registration-requests.component';

describe('AdminRegistrationRequestsComponent', () => {
  let component: AdminRegistrationRequestsComponent;
  let fixture: ComponentFixture<AdminRegistrationRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRegistrationRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRegistrationRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

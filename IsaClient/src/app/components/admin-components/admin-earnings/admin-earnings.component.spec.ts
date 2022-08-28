import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEarningsComponent } from './admin-earnings.component';

describe('AdminEarningsComponent', () => {
  let component: AdminEarningsComponent;
  let fixture: ComponentFixture<AdminEarningsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminEarningsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminEarningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

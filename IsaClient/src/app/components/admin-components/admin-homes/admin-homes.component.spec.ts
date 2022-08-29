import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHomesComponent } from './admin-homes.component';

describe('AdminHomesComponent', () => {
  let component: AdminHomesComponent;
  let fixture: ComponentFixture<AdminHomesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminHomesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminHomesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBoatsComponent } from './admin-boats.component';

describe('AdminBoatsComponent', () => {
  let component: AdminBoatsComponent;
  let fixture: ComponentFixture<AdminBoatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminBoatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBoatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

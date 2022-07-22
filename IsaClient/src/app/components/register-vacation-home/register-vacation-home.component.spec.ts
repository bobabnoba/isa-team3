import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterVacationHomeComponent } from './register-vacation-home.component';

describe('RegisterVacationHomeComponent', () => {
  let component: RegisterVacationHomeComponent;
  let fixture: ComponentFixture<RegisterVacationHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterVacationHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterVacationHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

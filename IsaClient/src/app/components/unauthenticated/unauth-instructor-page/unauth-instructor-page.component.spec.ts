import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthInstructorPageComponent } from './unauth-instructor-page.component';

describe('UnauthInstructorPageComponent', () => {
  let component: UnauthInstructorPageComponent;
  let fixture: ComponentFixture<UnauthInstructorPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthInstructorPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthInstructorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

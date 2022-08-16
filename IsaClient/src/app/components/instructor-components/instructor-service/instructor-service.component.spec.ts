import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorServiceComponent } from './instructor-service.component';

describe('InstructorServiceComponent', () => {
  let component: InstructorServiceComponent;
  let fixture: ComponentFixture<InstructorServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

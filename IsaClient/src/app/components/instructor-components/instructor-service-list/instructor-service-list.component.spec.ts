import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorServiceListComponent } from './instructor-service-list.component';

describe('InstructorServiceListComponent', () => {
  let component: InstructorServiceListComponent;
  let fixture: ComponentFixture<InstructorServiceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorServiceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorServiceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

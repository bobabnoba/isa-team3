import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureInstructorComponent } from './adventure-instructor.component';

describe('AdventureInstructorComponent', () => {
  let component: AdventureInstructorComponent;
  let fixture: ComponentFixture<AdventureInstructorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureInstructorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureInstructorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

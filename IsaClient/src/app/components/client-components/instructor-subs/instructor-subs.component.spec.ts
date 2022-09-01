import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorSubsComponent } from './instructor-subs.component';

describe('InstructorSubsComponent', () => {
  let component: InstructorSubsComponent;
  let fixture: ComponentFixture<InstructorSubsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorSubsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorSubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

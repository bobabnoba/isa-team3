import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorTopbarComponent } from './instructor-topbar.component';

describe('InstructorTopbarComponent', () => {
  let component: InstructorTopbarComponent;
  let fixture: ComponentFixture<InstructorTopbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorTopbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorTopbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

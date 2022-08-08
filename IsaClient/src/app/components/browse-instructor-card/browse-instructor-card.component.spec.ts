import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseInstructorCardComponent } from './browse-instructor-card.component';

describe('BrowseInstructorCardComponent', () => {
  let component: BrowseInstructorCardComponent;
  let fixture: ComponentFixture<BrowseInstructorCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseInstructorCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseInstructorCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

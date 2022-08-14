import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorOffersComponent } from './instructor-offers.component';

describe('InstructorOffersComponent', () => {
  let component: InstructorOffersComponent;
  let fixture: ComponentFixture<InstructorOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

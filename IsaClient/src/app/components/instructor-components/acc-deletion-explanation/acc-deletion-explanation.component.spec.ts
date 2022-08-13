import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccDeletionExplanationComponent } from './acc-deletion-explanation.component';

describe('AccDeletionExplanationComponent', () => {
  let component: AccDeletionExplanationComponent;
  let fixture: ComponentFixture<AccDeletionExplanationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccDeletionExplanationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccDeletionExplanationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

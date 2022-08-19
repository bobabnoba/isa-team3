import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerAccDeletionExplanationComponent } from './home-owner-acc-deletion-explanation.component';

describe('HomeOwnerAccDeletionExplanationComponent', () => {
  let component: HomeOwnerAccDeletionExplanationComponent;
  let fixture: ComponentFixture<HomeOwnerAccDeletionExplanationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerAccDeletionExplanationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerAccDeletionExplanationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

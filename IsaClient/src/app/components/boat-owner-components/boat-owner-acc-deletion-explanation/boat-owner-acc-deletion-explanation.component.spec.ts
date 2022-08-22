import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerAccDeletionExplanationComponent } from './boat-owner-acc-deletion-explanation.component';

describe('BoatOwnerAccDeletionExplanationComponent', () => {
  let component: BoatOwnerAccDeletionExplanationComponent;
  let fixture: ComponentFixture<BoatOwnerAccDeletionExplanationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerAccDeletionExplanationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerAccDeletionExplanationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

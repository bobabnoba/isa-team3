import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastExperiencesComponent } from './past-experiences.component';

describe('PastExperiencesComponent', () => {
  let component: PastExperiencesComponent;
  let fixture: ComponentFixture<PastExperiencesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastExperiencesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PastExperiencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

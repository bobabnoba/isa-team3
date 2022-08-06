import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthenticatedPageComponent } from './unauthenticated-page.component';

describe('UnauthenticatedPageComponent', () => {
  let component: UnauthenticatedPageComponent;
  let fixture: ComponentFixture<UnauthenticatedPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatedPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthenticatedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

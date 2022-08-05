import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationHomePageComponent } from './vacation-home-page.component';

describe('VacationHomePageComponent', () => {
  let component: VacationHomePageComponent;
  let fixture: ComponentFixture<VacationHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

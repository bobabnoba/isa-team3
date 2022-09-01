import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeAdditionalInfoComponent } from './home-additional-info.component';

describe('HomeAdditionalInfoComponent', () => {
  let component: HomeAdditionalInfoComponent;
  let fixture: ComponentFixture<HomeAdditionalInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeAdditionalInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeAdditionalInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

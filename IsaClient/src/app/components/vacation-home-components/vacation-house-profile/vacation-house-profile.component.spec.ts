import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationHouseProfileComponent } from './vacation-house-profile.component';

describe('VacationHouseProfileComponent', () => {
  let component: VacationHouseProfileComponent;
  let fixture: ComponentFixture<VacationHouseProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationHouseProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationHouseProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

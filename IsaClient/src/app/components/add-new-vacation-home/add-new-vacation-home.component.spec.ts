import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewVacationHomeComponent } from './add-new-vacation-home.component';

describe('AddNewVacationHomeComponent', () => {
  let component: AddNewVacationHomeComponent;
  let fixture: ComponentFixture<AddNewVacationHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewVacationHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewVacationHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

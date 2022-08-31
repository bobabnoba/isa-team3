import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerReportComponent } from './boat-owner-report.component';

describe('BoatOwnerReportComponent', () => {
  let component: BoatOwnerReportComponent;
  let fixture: ComponentFixture<BoatOwnerReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

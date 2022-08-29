import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatAdditionalInfoComponent } from './boat-additional-info.component';

describe('BoatAdditionalInfoComponent', () => {
  let component: BoatAdditionalInfoComponent;
  let fixture: ComponentFixture<BoatAdditionalInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatAdditionalInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatAdditionalInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

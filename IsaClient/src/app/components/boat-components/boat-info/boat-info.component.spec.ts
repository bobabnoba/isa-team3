import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatInfoComponent } from './boat-info.component';

describe('BoatInfoComponent', () => {
  let component: BoatInfoComponent;
  let fixture: ComponentFixture<BoatInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

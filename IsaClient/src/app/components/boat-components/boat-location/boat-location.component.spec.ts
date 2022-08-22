import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatLocationComponent } from './boat-location.component';

describe('BoatLocationComponent', () => {
  let component: BoatLocationComponent;
  let fixture: ComponentFixture<BoatLocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatLocationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

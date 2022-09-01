import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatSubsComponent } from './boat-subs.component';

describe('BoatSubsComponent', () => {
  let component: BoatSubsComponent;
  let fixture: ComponentFixture<BoatSubsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatSubsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatSubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

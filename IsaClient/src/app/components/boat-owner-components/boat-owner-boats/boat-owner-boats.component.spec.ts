import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerBoatsComponent } from './boat-owner-boats.component';

describe('BoatOwnerBoatsComponent', () => {
  let component: BoatOwnerBoatsComponent;
  let fixture: ComponentFixture<BoatOwnerBoatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerBoatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerBoatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

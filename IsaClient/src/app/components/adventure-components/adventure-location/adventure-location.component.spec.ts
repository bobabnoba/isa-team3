import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureLocationComponent } from './adventure-location.component';

describe('AdventureLocationComponent', () => {
  let component: AdventureLocationComponent;
  let fixture: ComponentFixture<AdventureLocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureLocationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

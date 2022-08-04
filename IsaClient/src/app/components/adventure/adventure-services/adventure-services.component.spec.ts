import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureServicesComponent } from './adventure-services.component';

describe('AdventureServicesComponent', () => {
  let component: AdventureServicesComponent;
  let fixture: ComponentFixture<AdventureServicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureServicesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

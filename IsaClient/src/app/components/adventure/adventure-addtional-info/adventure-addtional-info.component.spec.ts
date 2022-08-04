import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureAddtionalInfoComponent } from './adventure-addtional-info.component';

describe('AdventureAddtionalInfoComponent', () => {
  let component: AdventureAddtionalInfoComponent;
  let fixture: ComponentFixture<AdventureAddtionalInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureAddtionalInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureAddtionalInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

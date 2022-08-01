import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventurePreviewComponent } from './adventure-preview.component';

describe('AdventurePreviewComponent', () => {
  let component: AdventurePreviewComponent;
  let fixture: ComponentFixture<AdventurePreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventurePreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventurePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

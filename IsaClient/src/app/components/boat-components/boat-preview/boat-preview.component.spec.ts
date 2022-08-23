import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatPreviewComponent } from './boat-preview.component';

describe('BoatPreviewComponent', () => {
  let component: BoatPreviewComponent;
  let fixture: ComponentFixture<BoatPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

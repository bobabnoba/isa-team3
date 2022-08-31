import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePreviewComponent } from './home-preview.component';

describe('HomePreviewComponent', () => {
  let component: HomePreviewComponent;
  let fixture: ComponentFixture<HomePreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

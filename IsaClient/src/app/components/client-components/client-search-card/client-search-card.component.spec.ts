import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSearchCardComponent } from './client-search-card.component';

describe('ClientSearchCardComponent', () => {
  let component: ClientSearchCardComponent;
  let fixture: ComponentFixture<ClientSearchCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientSearchCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientSearchCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

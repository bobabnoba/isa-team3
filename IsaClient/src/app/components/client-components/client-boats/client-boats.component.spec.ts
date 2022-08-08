import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientBoatsComponent } from './client-boats.component';

describe('ClientBoatsComponent', () => {
  let component: ClientBoatsComponent;
  let fixture: ComponentFixture<ClientBoatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientBoatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientBoatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

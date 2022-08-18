import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientTopBarComponent } from './client-top-bar.component';

describe('ClientTopBarComponent', () => {
  let component: ClientTopBarComponent;
  let fixture: ComponentFixture<ClientTopBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientTopBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientTopBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

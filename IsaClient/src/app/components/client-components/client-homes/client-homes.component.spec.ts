import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientHomesComponent } from './client-homes.component';

describe('ClientHomesComponent', () => {
  let component: ClientHomesComponent;
  let fixture: ComponentFixture<ClientHomesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientHomesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientHomesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

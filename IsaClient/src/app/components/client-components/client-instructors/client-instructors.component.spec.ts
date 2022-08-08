import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientInstructorsComponent } from './client-instructors.component';

describe('ClientInstructorsComponent', () => {
  let component: ClientInstructorsComponent;
  let fixture: ComponentFixture<ClientInstructorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientInstructorsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientInstructorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientBrowseComponent } from './client-browse.component';

describe('ClientBrowseComponent', () => {
  let component: ClientBrowseComponent;
  let fixture: ComponentFixture<ClientBrowseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientBrowseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientBrowseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

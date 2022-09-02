import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerHomesComponent } from './home-owner-homes.component';

describe('HomeOwnerHomesComponent', () => {
  let component: HomeOwnerHomesComponent;
  let fixture: ComponentFixture<HomeOwnerHomesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerHomesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerHomesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

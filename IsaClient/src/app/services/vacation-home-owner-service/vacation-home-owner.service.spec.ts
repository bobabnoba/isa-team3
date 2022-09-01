import { TestBed } from '@angular/core/testing';

import { VacationHomeOwnerService } from './vacation-home-owner.service';

describe('VacationHomeOwnerService', () => {
  let service: VacationHomeOwnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacationHomeOwnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

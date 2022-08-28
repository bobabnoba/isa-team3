import { TestBed } from '@angular/core/testing';

import { ReservationSearchService } from './reservation-search.service';

describe('ReservationSearchService', () => {
  let service: ReservationSearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationSearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

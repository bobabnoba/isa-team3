import { TestBed } from '@angular/core/testing';

import { VacationHomeService } from './vacation-home.service';

describe('VacationHomeService', () => {
  let service: VacationHomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacationHomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

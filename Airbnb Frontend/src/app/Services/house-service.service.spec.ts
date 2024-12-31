import { TestBed } from '@angular/core/testing';

import { HouseServiceService } from './house-service.service';

describe('HouseServiceService', () => {
  let service: HouseServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HouseServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

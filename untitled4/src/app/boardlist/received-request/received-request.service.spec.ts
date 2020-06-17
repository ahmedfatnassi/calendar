import { TestBed } from '@angular/core/testing';

import { ReceivedRequestService } from './received-request.service';

describe('ReceivedRequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReceivedRequestService = TestBed.get(ReceivedRequestService);
    expect(service).toBeTruthy();
  });
});

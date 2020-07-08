import { TestBed } from '@angular/core/testing';

import { BoardlistService } from './boardlist.service';

describe('BoardlistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BoardlistService = TestBed.get(BoardlistService);
    expect(service).toBeTruthy();
  });
});

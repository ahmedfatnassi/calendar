import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletedRequestComponent } from './completed-request.component';

describe('CompletedRequestComponent', () => {
  let component: CompletedRequestComponent;
  let fixture: ComponentFixture<CompletedRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompletedRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletedRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceivedRequestComponent } from './received-request.component';

describe('ReceivedRequestComponent', () => {
  let component: ReceivedRequestComponent;
  let fixture: ComponentFixture<ReceivedRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReceivedRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReceivedRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

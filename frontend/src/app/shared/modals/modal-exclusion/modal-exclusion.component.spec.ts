import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalExclusionComponent } from './modal-exclusion.component';

describe('ModalExclusionComponent', () => {
  let component: ModalExclusionComponent;
  let fixture: ComponentFixture<ModalExclusionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalExclusionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalExclusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

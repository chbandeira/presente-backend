import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-modal-exclusion',
  templateUrl: './modal-exclusion.component.html',
  styleUrls: ['./modal-exclusion.component.scss']
})
export class ModalExclusionComponent implements OnInit {

  @Input() id: number;
  @Output() eventModal = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  emitDelete(id: number) {
    this.eventModal.emit(id);
  }
}

import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Page } from './pagination.model';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit {

  @Input() page: Page;
  @Output() eventPage = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  createArray(num: number) {
    return Array.from({length: num}, (x, i) => i);
  }

  emitPage(numPage: number) {
    if (this.page.number !== numPage) {
      this.eventPage.emit(numPage);
    }
  }

}

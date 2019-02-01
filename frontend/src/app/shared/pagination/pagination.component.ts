import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PaginationResponse } from './pagination.model';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit {

  @Input() paginationResponse: PaginationResponse;
  @Output() eventPage = new EventEmitter<number>();
  currentPage: number;
  previousPage: number;

  constructor() { }

  ngOnInit() {
    this.currentPage = this.paginationResponse.number + 1;
  }

  emitPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.eventPage.emit(page - 1);
    }
  }

}

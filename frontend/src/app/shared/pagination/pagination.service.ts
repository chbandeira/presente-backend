import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Pagination } from './pagination.model';

@Injectable({
  providedIn: 'root'
})
export class PaginationService {

  constructor() { }

  getHttpParamsToSearch(pagination: Pagination): HttpParams {
    let httpParams = new HttpParams();
    if (pagination.page) {
      httpParams = httpParams.set('page', String(pagination.page));
    }
    if (pagination.size) {
      httpParams = httpParams.set('size', String(pagination.size));
    }
    if (pagination.directon) {
      httpParams = httpParams.set('directon', pagination.directon);
    }
    if (pagination.orderBy) {
      httpParams = httpParams.set('orderBy', pagination.orderBy);
    }
    return httpParams;
  }
}

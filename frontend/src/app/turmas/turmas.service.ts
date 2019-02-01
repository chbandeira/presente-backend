import { Turma } from './turma/turma.model';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Pagination, PaginationResponse } from '../shared/pagination/pagination.model';
import { PaginationService } from '../shared/pagination/pagination.service';

const url = '/api/turmas';
const params = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TurmasService {

  constructor(private http: HttpClient, private paginationService: PaginationService) { }

  searchByName(term: string) {
    if (term === '') {
      return of([]);
    }

    const httpParams = new HttpParams().set('descricao', term);
    return this.http.get<any>(url, {
      params: httpParams
    }).pipe(
      map(x => x['content'])
    );
  }

  search(turma?: Turma, pagination?: Pagination): Observable<PaginationResponse> {
    let httpParams = new HttpParams();
    if (pagination) {
      httpParams = this.paginationService.getHttpParamsToSearch(pagination);
    }
    if (turma) {
      httpParams = this.setHttpParamsToSearch(turma, httpParams);
    }
    return this.http.get<PaginationResponse>(url, {
      params: httpParams
    });
  }

  setHttpParamsToSearch(turma: Turma, httpParams: HttpParams): HttpParams {
    if (turma.descricao) {
      httpParams = httpParams.set('descricao', turma.descricao);
    }
    if (turma.sala) {
      httpParams = httpParams.set('sala', turma.sala);
    }
    if (turma.serie) {
      httpParams = httpParams.set('serie', turma.serie);
    }
    if (turma.turno) {
      httpParams = httpParams.set('turno', turma.turno.toString());
    }
    return httpParams;
  }

  save(turma: Turma): Observable<number> {
    if (turma.id) {
      return this.http.put<number>(url, turma, params);
    }
    return this.http.post<number>(url, turma, params);
  }

  delete(id: number) {
    return this.http.delete(url + '/' + id);
  }

  getTurma(id: number): Observable<Turma> {
    return this.http.get<Turma>(url + '/' + id);
  }
}

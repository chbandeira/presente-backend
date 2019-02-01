import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { PaginationService } from '../shared/pagination/pagination.service';
import { Pagination, PaginationResponse } from '../shared/pagination/pagination.model';
import { Observable } from 'rxjs';
import { Aluno } from './aluno/aluno.model';

const url = '/api/alunos';
const params = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AlunosService {

  constructor(
    private http: HttpClient,
    private paginationService: PaginationService) { }

  search(aluno?: Aluno, pagination?: Pagination): Observable<PaginationResponse> {
    let httpParams = new HttpParams();
    if (pagination) {
      httpParams = this.paginationService.getHttpParamsToSearch(pagination);
    }
    if (aluno) {
      httpParams = this.setHttpParamsToSearch(aluno, httpParams);
    }
    return this.http.get<PaginationResponse>(url, {
      params: httpParams
    });
  }

  setHttpParamsToSearch(aluno: Aluno, httpParams: HttpParams): HttpParams {
    if (aluno.nome) {
      httpParams = httpParams.set('nome', aluno.nome);
    }
    if (aluno.anoLetivo) {
      httpParams = httpParams.set('anoLetivo', String(aluno.anoLetivo));
    }
    if (aluno.matricula) {
      httpParams = httpParams.set('matricula', aluno.matricula);
    }
    return httpParams;
  }

  save(aluno: Aluno, fileToUpload: File): Observable<number> {
    if (aluno.cpf && aluno.cpf.trim() === '') {
      aluno.cpf = null;
    }

    if (fileToUpload) {
      const formData: FormData = new FormData();
      formData.append('image', fileToUpload, fileToUpload.name);
      // TODO foto in server?
      aluno.urlFoto = fileToUpload.name;
    }

    if (aluno.id) {
      return this.http.put<number>(url, aluno, params);
    }
    return this.http.post<number>(url, aluno, params);
  }

  getAluno(id: number): Observable<Aluno> {
    return this.http.get<Aluno>(url + '/' + id);
  }

  delete(id: number) {
    return this.http.delete(url + '/' + id);
  }
}

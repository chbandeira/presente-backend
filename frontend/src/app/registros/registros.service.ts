import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistroEnum } from './registro/registro.enum';

const url = '/api/registros';
const params = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RegistrosService {

  constructor(private http: HttpClient) { }

  registrar(tipoRegistro: string, matricula: string): Observable<any> {
    let urlPost: string;
    if (this.isTipoEntrada(tipoRegistro)) {
      urlPost = `${url}/entrada`;
    } else if (this.isTipoSaida(tipoRegistro)) {
      urlPost = `${url}/saida`;
    }
    return this.http.post(urlPost, { matricula: matricula }, params);
  }

  isTipoEntrada(tipoRegistro: string): boolean {
    return tipoRegistro === RegistroEnum[RegistroEnum.entrada];
  }

  isTipoSaida(tipoRegistro: string): boolean {
    return tipoRegistro === RegistroEnum[RegistroEnum.saida];
  }
}

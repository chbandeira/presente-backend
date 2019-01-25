import { Registro } from './registro/registro.model';
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

  registrar(registro: Registro): Observable<any> {
    return this.http.post(url, {
      matricula: registro.matricula,
      tipoRegistro: registro.tipoRegistro
    }, params);
  }
}

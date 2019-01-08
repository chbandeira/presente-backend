import { MessageEnum } from './../../shared/messages/message.enum';
import { Component, OnInit, OnChanges, SimpleChanges, DoCheck } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RegistrosService } from '../registros.service';
import { RegistroEnum } from './registro.enum';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit, DoCheck {

  tipoRegistro: string;
  title = 'Entrada';
  submitForm: FormGroup;
  matriculaError: string;
  showMessage: boolean;
  formValid: boolean;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private registrosService: RegistrosService) { }

  ngOnInit() {
    this.tipoRegistro = this.route.snapshot.params['tipoRegistro'];
    this.submitForm = this.fb.group({
      matricula: ['']
    });
  }

  ngDoCheck()	{
    if (this.tipoRegistro !== this.route.snapshot.params['tipoRegistro']) {
      this.tipoRegistro = this.route.snapshot.params['tipoRegistro'];
      this.submitForm = this.fb.group({
        matricula: ['']
      });
      this.formValid = false;
      this.showMessage = false;
      this.matriculaError = '';
    }
    this.title = this.route.snapshot.params['tipoRegistro'] === RegistroEnum[RegistroEnum.saida] ? 'Saída' : 'Entrada';
  }

  registrar() {
    if (!this.submitForm.value.matricula) {
      this.matriculaError = 'Informe a matrícula para registro';
      this.formValid = false;
      return;
    }
    this.registrosService.registrar(this.route.snapshot.params['tipoRegistro'], this.submitForm.value.matricula)
      .subscribe(() => {
        this.showMessage = true;
        this.formValid = true;
      }, err => {
        this.formValid = false;
        this.showMessage = true;
        this.matriculaError = err.error.msg;
      });
  }

  clean() {
    this.matriculaError = '';
    this.formValid = false;
  }

  getMessageEnum(): MessageEnum {
    if (this.submitForm.invalid || !this.formValid) {
      return MessageEnum.error;
    }
    return MessageEnum.success;
  }

}

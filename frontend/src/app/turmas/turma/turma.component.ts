import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { TurmasService } from '../turmas.service';
import { Turma } from './turma.model';
import { MessageTypes } from '../../shared/messages/message-form/message-types';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-turma',
  templateUrl: './turma.component.html',
  styleUrls: ['./turma.component.scss']
})
export class TurmaComponent implements OnInit {

  submitForm: FormGroup;
  showMessage = false;
  turmaAlreadyNew = false;
  editMode = false;
  formValid = false;
  turma: Turma;

  constructor(
    private turmasService: TurmasService, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.turma = new Turma();
    //this.alunoErrors = new AlunoErrors();
    this.startForm();
    this.turma.id = this.route.snapshot.params['id'];
    if (this.turma.id) {
      this.editMode = true;
      this.turmasService.getTurma(this.turma.id).subscribe(t => {
        this.turma = t;
        this.startForm();
      });
    }
  }

  private startForm() {
    this.submitForm = this.formBuilder.group({
      descricao: [this.turma.descricao],
      serie: [this.turma.serie],
      sala: [this.turma.sala],
      turno: [this.turma.turno]
    });
  }

  title(): string {
    return this.turma.id ? 'Alterar' : 'Nova';
  }

  typeOfMessage(): string {
    if (this.submitForm.invalid || !this.formValid) {
      return MessageTypes.error;
    }
    return MessageTypes.success;
  }

  newTurma() {
    this.turma = new Turma();
    this.startForm();
    this.showMessage = false;
    this.editMode = false;
    this.turmaAlreadyNew = false;
  }

  clean() {
    this.showMessage = false;
    this.startForm();
  }

  private getTurmaFromForm(): Turma {
    const turma: Turma = this.submitForm.value;
    turma.id = this.turma.id;
    return turma;
  }

  save() {
    //this.alunoErrors = new AlunoErrors();
    if (this.submitForm.valid) {
      const turma = this.getTurmaFromForm();
      this.turmasService.save(turma).subscribe(id => {
        if (Number(id)) {
          if (!this.turma.id) {
            this.turmaAlreadyNew = true;
          }
          this.turma.id = id;
          this.editMode = true;
          this.formValid = true;
          this.showMessage = true;
        }
      }, err => {
        this.formValid = false;
        this.showMessage = true;
        err.error.errors.forEach(e => {
          switch (e.fieldName) {
            default:
              break;
          }
        });
      });
    }
  }

}

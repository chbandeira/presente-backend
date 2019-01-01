import { Masks } from './../../shared/formatter/masks';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AlunosService } from '../alunos.service';
import { Aluno } from './aluno.model';
import { ActivatedRoute } from '@angular/router';
import { NgbDatePtParserFormatter } from './../../shared/formatter/ngb-date-pt-parser-formatter';
import { MessageTypes } from '../../shared/messages/message-form/message-types';
import { AlunoErrors } from './aluno.errors';
import { Observable, of } from 'rxjs';
import { debounceTime, distinctUntilChanged, tap, switchMap, catchError } from 'rxjs/operators';
import { TurmasService } from '../../turmas/turmas.service';
import { TurmaFormatter } from '../../turmas/turma/turma.formatter';
import { Turma } from '../../turmas/turma/turma.model';


@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.scss']
})
export class AlunoComponent implements OnInit {

  submitForm: FormGroup;
  fileToUpload: File;
  aluno: Aluno;
  alunoErrors: AlunoErrors;

  editMode = false;
  alunoAlreadyNew = false;
  showMessage = false;
  formValid = false;

  turmaFormatter: TurmaFormatter;
  searching = false;
  searchFailed = false;

  telefoneCelularMask = Masks.telefoneCelular;
  telefoneFixoMask = Masks.telefoneFixo;
  cpfMask = Masks.CPF;

  constructor(
    private alunosService: AlunosService,
    private turmasService: TurmasService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private dateFormatter: NgbDatePtParserFormatter) { }

  ngOnInit() {
    this.aluno = new Aluno();
    this.alunoErrors = new AlunoErrors();
    this.startForm();
    this.aluno.id = this.route.snapshot.params['id'];
    if (this.aluno.id) {
      this.editMode = true;
      this.alunosService.getAluno(this.aluno.id).subscribe(a => {
        this.aluno = a;
        this.startForm();
      });
    }
  }

  private startForm() {
    this.submitForm = this.formBuilder.group({
      nome: [this.aluno.nome],
      dataNascimento: [this.dateFormatter.parse(this.aluno.dataNascimento)],
      matricula: [this.aluno.matricula],
      serie: [this.aluno.serie],
      turma: [this.aluno.turma],
      sala: [this.aluno.sala],
      turno: [this.aluno.turno],
      alunoBolsista: [this.aluno.alunoBolsista],
      nomeResponsavel: [this.aluno.nomeResponsavel],
      cpf: [this.aluno.cpf],
      email: [this.aluno.email],
      email2: [this.aluno.email2],
      telefoneFixo: [this.aluno.telefoneFixo],
      telefoneCelular: [this.aluno.telefoneCelular],
      enviarEmail: [this.aluno.enviarEmail],
      // TODO enviarMensagem
      urlFoto: [null]
    });
    this.turmaFormatter = new TurmaFormatter(this.submitForm);
  }

  title(): string {
    return this.aluno.id ? 'Alterar' : 'Novo';
  }

  save() {
    this.alunoErrors = new AlunoErrors();
    if (this.submitForm.valid) {
      const aluno = this.getAlunoFromForm();
      this.alunosService.save(aluno, this.fileToUpload).subscribe(id => {
        if (Number(id)) {
          this.submitForm.value.dataNascimento = this.dateFormatter.parse(this.aluno.dataNascimento);
          if (!this.aluno.id) {
            this.alunoAlreadyNew = true;
          }
          this.aluno.id = id;
          this.editMode = true;
          this.formValid = true;
          this.showMessage = true;
        }
      }, err => {
        this.formValid = false;
        this.showMessage = true;
        if (err.error.errors) {
          err.error.errors.forEach(e => {
            switch (e.fieldName) {
              case 'nome':
                this.alunoErrors.nome = e.messageString;
                break;
              case 'matricula':
                this.alunoErrors.matricula = e.messageString;
                break;
              case 'cpf':
                this.alunoErrors.cpf = e.messageString;
                break;
              case 'dataNascimento':
                this.alunoErrors.dataNascimento = e.messageString;
                break;
              case 'email':
                this.alunoErrors.email = e.messageString;
                break;
              case 'email2':
                this.alunoErrors.email2 = e.messageString;
                break;
              default:
                break;
            }
          });
        }
      });
    }
  }

  private getAlunoFromForm(): Aluno {
    const aluno: Aluno = this.submitForm.value;
    console.log(aluno)
    aluno.dataNascimento = this.dateFormatter.format(this.submitForm.value.dataNascimento);
    aluno.id = this.aluno.id;
    return aluno;
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  clean() {
    this.showMessage = false;
    this.startForm();
  }

  newAluno() {
    this.aluno = new Aluno();
    this.startForm();
    this.showMessage = false;
    this.editMode = false;
    this.alunoAlreadyNew = false;
  }

  typeOfMessage(): string {
    if (this.submitForm.invalid || !this.formValid) {
      return MessageTypes.error;
    }
    return MessageTypes.success;
  }

  searchTurma = (text: Observable<string>) =>
    text.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      tap(() => this.searching = true),
      switchMap(term =>
        this.turmasService.searchByName(term).pipe(
          tap(() => this.searchFailed = false),
          catchError(() => {
            this.searchFailed = true;
            return of([]);
          }))
      ),
      tap(() => this.searching = false)
    );
}

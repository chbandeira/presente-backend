import { Pagination, Page } from './../shared/pagination/pagination.model';
import { AlunosService } from './alunos.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss']
})
export class AlunosComponent implements OnInit {

  alunosPagination: Observable<Page>;
  searchForm: FormGroup;

  constructor(
    private alunosService: AlunosService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      nome: this.formBuilder.control(''),
      matricula: this.formBuilder.control(''),
      anoLetivo: this.formBuilder.control('')
    });
    this.alunosPagination = this.alunosService.search();
  }

  search() {
    this.alunosPagination = this.alunosService.search(this.searchForm.value);
  }

  onPage(event: any) {
    const pagination: Pagination = new Pagination();
    pagination.page = event;
    this.alunosPagination = this.alunosService.search(this.searchForm.value, pagination);
  }

  onDelete(id: number) {
    this.alunosService.delete(id).subscribe(() => this.search());
  }
}

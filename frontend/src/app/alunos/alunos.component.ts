import { Pagination, PaginationResponse } from './../shared/pagination/pagination.model';
import { AlunosService } from './alunos.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import { finalize} from 'rxjs/operators';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss']
})
export class AlunosComponent implements OnInit {

  alunosPagResponse: Observable<PaginationResponse>;
  searchForm: FormGroup;
  loading: boolean;

  constructor(
    private alunosService: AlunosService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      nome: this.formBuilder.control(''),
      matricula: this.formBuilder.control(''),
      anoLetivo: this.formBuilder.control('')
    });
    this.loading = true;
    this.alunosPagResponse = this.alunosService.search().pipe(
      finalize(() => this.loading = false)
    );
  }

  search() {
    this.loading = true;
    this.alunosPagResponse = this.alunosService.search(this.searchForm.value).pipe(
      finalize(() => this.loading = false)
    );
  }

  onPage(event: any) {
    const pagination: Pagination = new Pagination();
    pagination.page = event;
    this.loading = true;
    this.alunosPagResponse = this.alunosService.search(this.searchForm.value, pagination).pipe(
      finalize(() => this.loading = false)
    );
  }

  onDelete(id: number) {
    this.alunosService.delete(id).subscribe(() => this.search());
  }
}

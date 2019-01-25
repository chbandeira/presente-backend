import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { TurmasService } from './turmas.service';
import { Observable } from 'rxjs';
import { PaginationResponse, Pagination } from '../shared/pagination/pagination.model';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-turmas',
  templateUrl: './turmas.component.html',
  styleUrls: ['./turmas.component.scss']
})
export class TurmasComponent implements OnInit {

  searchForm: FormGroup;
  turmasPagResponse: Observable<PaginationResponse>;
  loading: boolean;

  constructor(private formBuilder: FormBuilder, private turmasService: TurmasService) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      descricao: [''],
      serie: [''],
      sala: [''],
      turno: ['']
    });
    this.loading = true;
    this.turmasPagResponse = this.turmasService.search().pipe(
      finalize(() => this.loading = false)
    );
  }

  search() {
    this.loading = true;
    this.turmasPagResponse = this.turmasService.search(this.searchForm.value).pipe(
      finalize(() => this.loading = false)
    );
  }

  onPage(event: any) {
    const pagination: Pagination = new Pagination();
    pagination.page = event;
    this.loading = true;
    this.turmasPagResponse = this.turmasService.search(this.searchForm.value, pagination).pipe(
      finalize(() => this.loading = false)
    );
  }

  onDelete(id: number) {
    this.turmasService.delete(id).subscribe(() => this.search());
  }

}

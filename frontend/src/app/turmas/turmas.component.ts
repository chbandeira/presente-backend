import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { TurmasService } from './turmas.service';
import { Observable } from 'rxjs';
import { Page, Pagination } from '../shared/pagination/pagination.model';

@Component({
  selector: 'app-turmas',
  templateUrl: './turmas.component.html',
  styleUrls: ['./turmas.component.scss']
})
export class TurmasComponent implements OnInit {

  searchForm: FormGroup;
  turmasPagination: Observable<Page>;

  constructor(private formBuilder: FormBuilder, private turmasService: TurmasService) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      descricao: [''],
      serie: [''],
      sala: [''],
      turno: ['']
    });
    this.turmasPagination = this.turmasService.search();
  }

  search() {
    this.turmasPagination = this.turmasService.search(this.searchForm.value);
  }

  onPage(event: any) {
    const pagination: Pagination = new Pagination();
    pagination.page = event;
    this.turmasPagination = this.turmasService.search(this.searchForm.value, pagination);
  }

  onDelete(id: number) {
    this.turmasService.delete(id).subscribe(() => this.search());
  }

}

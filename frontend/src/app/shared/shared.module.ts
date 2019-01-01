import { NgModule } from '@angular/core';
import { PaginationService } from './pagination/pagination.service';
import { PaginationComponent } from './pagination/pagination.component';
import { SnackbarComponent } from './messages/snackbar/snackbar.component';
import { NotificationService } from './messages/notification.service';
import { SimNaoPipe } from './pipes/sim-nao.pipe';
import { MessageFormComponent } from './messages/message-form/message-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbDatepickerModule, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDatePtParserFormatter } from './formatter/ngb-date-pt-parser-formatter';
import { CommonModule } from '@angular/common';
import { TurnoPipe } from './pipes/turno.pipe';
import { TextMaskModule } from 'angular2-text-mask';
import { ModalExclusionComponent } from './modals/modal-exclusion/modal-exclusion.component';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [
    SimNaoPipe,
    TurnoPipe,
    PaginationComponent,
    SnackbarComponent,
    MessageFormComponent,
    ModalExclusionComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbDatepickerModule,
    TextMaskModule,
    AppRoutingModule
  ],
  exports: [
    TextMaskModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbDatepickerModule,
    SimNaoPipe,
    TurnoPipe,
    PaginationComponent,
    SnackbarComponent,
    MessageFormComponent,
    ModalExclusionComponent,
    AppRoutingModule
  ],
  providers: [
    PaginationService,
    NotificationService,
    NgbDatePtParserFormatter,
    { provide: NgbDateParserFormatter, useClass: NgbDatePtParserFormatter }
  ]
})
export class SharedModule { }

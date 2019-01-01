import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TurmasService } from './turmas.service';
import { TurmaComponent } from './turma/turma.component';
import { TurmasComponent } from './turmas.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    TurmaComponent,
    TurmasComponent
  ],
  imports: [
    SharedModule
  ],
  providers: [TurmasService]
})
export class TurmasModule { }

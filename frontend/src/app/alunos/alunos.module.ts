import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { AlunoComponent } from './aluno/aluno.component';
import { AlunosComponent } from './alunos.component';
import { AlunosService } from './alunos.service';
import { NgbTypeaheadModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AlunosComponent,
    AlunoComponent,
  ],
  imports: [
    SharedModule,
    NgbTypeaheadModule
  ],
  providers: [AlunosService]
})
export class AlunosModule { }

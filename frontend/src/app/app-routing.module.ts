import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AlunoComponent } from './alunos/aluno/aluno.component';
import { AlunosComponent } from './alunos/alunos.component';
import { TurmasComponent } from './turmas/turmas.component';
import { TurmaComponent } from './turmas/turma/turma.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'aluno', component: AlunoComponent },
  { path: 'aluno/:id', component: AlunoComponent },
  { path: 'alunos', component: AlunosComponent },
  { path: 'turma', component: TurmaComponent },
  { path: 'turma/:id', component: TurmaComponent },
  { path: 'turmas', component: TurmasComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlunoComponent } from './aluno/aluno.component';
import { AlunosComponent } from './alunos.component';

const routes: Routes = [
  { path: '', component: AlunosComponent },
  { path: 'cadastro', component: AlunoComponent },
  { path: 'cadastro/:id', component: AlunoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlunosRoutingModule { }

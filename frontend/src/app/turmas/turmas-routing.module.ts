import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TurmasComponent } from './turmas.component';
import { TurmaComponent } from './turma/turma.component';

const routes: Routes = [
  { path: '', component: TurmasComponent },
  { path: 'cadastro', component: TurmaComponent },
  { path: 'cadastro/:id', component: TurmaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TurmasRoutingModule { }

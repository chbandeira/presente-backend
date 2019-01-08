import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'alunos', loadChildren: './alunos/alunos.module#AlunosModule' },
  { path: 'turmas', loadChildren: './turmas/turmas.module#TurmasModule' },
  { path: 'registros', loadChildren: './registros/registros.module#RegistrosModule' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

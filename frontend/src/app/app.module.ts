import { NgModule, ErrorHandler } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplicationErrorHandler } from './app.erro-handler';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { AlunosModule } from './alunos/alunos.module';
import { HomeComponent } from './home/home.component';
import { TurmasModule } from './turmas/turmas.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    SharedModule,
    AlunosModule,
    TurmasModule
  ],
  providers: [
    { provide: ErrorHandler, useClass: ApplicationErrorHandler }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

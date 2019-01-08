import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrosRoutingModule } from './registros-routing.module';
import { SharedModule } from '../shared/shared.module';
import { RegistroComponent } from './registro/registro.component';
import { RegistrosService } from './registros.service';

@NgModule({
  declarations: [RegistroComponent],
  imports: [
    CommonModule,
    SharedModule,
    RegistrosRoutingModule
  ],
  providers: [RegistrosService]
})
export class RegistrosModule { }

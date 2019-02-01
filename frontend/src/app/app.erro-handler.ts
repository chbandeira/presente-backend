import { Injectable, Injector, NgZone, ErrorHandler } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from './shared/messages/notification.service';

@Injectable()
export class ApplicationErrorHandler extends ErrorHandler {

  constructor(private ns: NotificationService, private injector: Injector, private zone: NgZone) {
    super();
  }

  handleError(errorResponse: HttpErrorResponse | any) {

    if (errorResponse instanceof HttpErrorResponse) {
      const message = errorResponse.error.msg;
      this.zone.run(() => {
        const codStatus = errorResponse.status;
        switch (errorResponse.status) {
          case 400:
            this.ns.notify(message || `Erro ${codStatus} - Solicitação inválida.`);
            break;
          case 403:
            this.ns.notify(message || `Erro ${codStatus} - Acesso negado.`);
            break;
          case 404:
            this.ns.notify(message || `Erro ${codStatus} - Recurso não encontrado.`);
            break;
          default:
            this.ns.notify(message || `Erro ${codStatus} - Erro inesperado.`);
            break;
        }
      });
    }

    super.handleError(errorResponse);
  }
}

import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'turno'
})
export class TurnoPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (value === 'M') {
      return 'Matutino';
    } else if (value === 'V') {
      return 'Vespertino';
    } else if (value === 'N') {
      return 'Noturno';
    }
  }

}

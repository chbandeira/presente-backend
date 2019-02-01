import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'turno'
})
export class TurnoPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (value === 0) {
      return 'Matutino';
    } else if (value === 1) {
      return 'Vespertino';
    } else if (value === 2) {
      return 'Noturno';
    }
  }

}

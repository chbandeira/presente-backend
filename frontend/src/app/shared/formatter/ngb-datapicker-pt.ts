import { Injectable } from '@angular/core';
import { NgbDatepickerI18n, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

const i18nValues = {
    weekdays: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
    months: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    monthsFull: ['Janeiro', 'Feveveiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro']
};

@Injectable()
export class NgbDatepickerPt extends NgbDatepickerI18n {

    getWeekdayShortName(weekday: number): string {
        return i18nValues.weekdays[weekday - 1];
    }

    getMonthShortName(month: number): string {
        return i18nValues.months[month - 1];
    }

    getMonthFullName(month: number): string {
        return i18nValues.monthsFull[month - 1];
    }

    getDayAriaLabel(date: NgbDateStruct): string {
        return date.day.toString();
    }
}

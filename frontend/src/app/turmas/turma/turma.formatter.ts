import { Turma } from './turma.model';
import { TurnoPipe } from '../../shared/pipes/turno.pipe';
import { FormGroup } from '@angular/forms';

export class TurmaFormatter {

    constructor(private submitForm: FormGroup) { }

    formatterTurmaResult = (t: Turma) => {
        let text = `Turma: ${t.descricao ? t.descricao : '-'}`;
        text = `${text} | Série: ${t.serie ? t.serie : '-'}`;
        text = `${text} | Sala: ${t.sala ? t.sala : '-'}`;
        text = `${text} | Turno: ${t.turno ? new TurnoPipe().transform(t.turno) : '-'}`;
        return text;
    }

    formatterTurmaInput = (t: Turma) => {
        this.submitForm.get('turma').setValue(t.descricao);
        this.submitForm.get('serie').setValue(t.serie);
        this.submitForm.get('sala').setValue(t.sala);
        this.submitForm.get('turno').setValue(t.turno);
        return null; // customized
    }

}

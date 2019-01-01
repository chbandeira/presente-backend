import { Turma } from "./turma.model";
import { TurnoPipe } from "../../shared/pipes/turno.pipe";
import { FormGroup } from "@angular/forms";

export class TurmaFormatter {

    constructor(private submitForm: FormGroup) { }

    formatterTurmaResult = (t: Turma) => {
        return `Turma: ${t.descricao ? t.descricao : '-'} | Série: ${t.serie ? t.serie : '-'} | Sala: ${t.sala ? t.sala : '-'} | Turno: ${t.turno ? new TurnoPipe().transform(t.turno) : '-'}`;
    };

    formatterTurmaInput = (t: Turma) => {
        this.submitForm.get('turma').setValue(t.descricao);
        this.submitForm.get('serie').setValue(t.serie);
        this.submitForm.get('sala').setValue(t.sala);
        this.submitForm.get('turno').setValue(t.turno);
        return null;
    };

}
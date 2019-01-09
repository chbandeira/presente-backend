import { MessageEnum } from './../message.enum';
import { Component, OnInit, Input } from '@angular/core';
import { FormValidation } from '../../form-validation';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.scss']
})
export class MessageFormComponent implements OnInit {

  @Input() formValidation: FormValidation;

  constructor() { }

  ngOnInit() {
  }

  isSuccess(): boolean {
    return MessageEnum.success === this.formValidation.getMessageEnum();
  }

  isError(): boolean {
    return MessageEnum.error === this.formValidation.getMessageEnum();
  }

  getMessage(): string {
    if (!this.formValidation.message) {
      if (this.isError()) {
        return 'Ops! Ocorreu algum erro!';
      } else if (this.isSuccess()) {
        return 'Salvo com sucesso!';
      }
    } else {
      return this.formValidation.message;
    }
  }
}

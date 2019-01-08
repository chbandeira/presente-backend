import { MessageEnum } from './../message.enum';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.scss']
})
export class MessageFormComponent implements OnInit {

  @Input() showMessage: boolean;
  @Input() messageEnum: number;
  @Input() message: string;

  constructor() { }

  ngOnInit() {
  }

  isSuccess(): boolean {
    return MessageEnum.success === this.messageEnum;
  }

  isError(): boolean {
    return MessageEnum.error === this.messageEnum;
  }

  getMessage(): string {
    return this.message === undefined ? 'Campos obrigatórios ou inválidos!' : this.message;
  }
}

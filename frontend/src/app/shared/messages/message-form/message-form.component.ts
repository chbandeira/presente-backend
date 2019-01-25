import { Component, OnInit, Input } from '@angular/core';
import { FormValidation } from '../../form-validation';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.scss']
})
export class MessageFormComponent {

  @Input() formValidation: FormValidation;

  close() {
    this.formValidation.reset();
  }
}

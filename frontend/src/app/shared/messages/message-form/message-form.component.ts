import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.scss']
})
export class MessageFormComponent implements OnInit {

  @Input() showMessage: boolean;
  @Input() typeMessage: string;

  constructor() { }

  ngOnInit() {
  }

}

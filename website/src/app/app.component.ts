import { NOTIFICATIONS_OPTIONS } from './shared/constants';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  notifications_options = NOTIFICATIONS_OPTIONS;

  constructor() { }

}

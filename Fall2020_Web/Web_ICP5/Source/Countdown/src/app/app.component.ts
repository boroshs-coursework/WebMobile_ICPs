import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template:   `<div style="text-align:center">
              <h1>Welcome to {{ title }}!</h1>
              </div>
              <app-clock></app-clock>`,
  styles: ['h1{text-align: center;}']
})
export class AppComponent {
  title = 'Countdown';
}
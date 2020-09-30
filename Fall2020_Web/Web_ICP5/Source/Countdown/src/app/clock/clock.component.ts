import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clock',
  templateUrl: './clock.component.html',
  styleUrls: ['./clock.component.css']
})

export class ClockComponent implements OnInit {
  months = "000";
  weeks = "000";
  days = "000";
  hours = "000";
  mins = "000";
  secs = "000";

  constructor() {
    this.months = "";
    this.weeks = "";
    this.days = "";
    this.hours = "";
    this.mins = "";
    this.secs = "";
   }

  ngOnInit() {
  }
  
  countDownTo(inputMonth:string, inputDay:string, inputYear:string): void{
    this.months = inputMonth;
    this.weeks = "111";
    this.days = inputDay;
    this.hours = inputYear;
    this.mins = "111";
    this.secs = "111";
  }
}

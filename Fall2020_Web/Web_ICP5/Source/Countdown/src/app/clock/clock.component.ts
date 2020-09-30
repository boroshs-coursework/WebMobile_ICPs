import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clock',
  templateUrl: './clock.component.html',
  styles: ['#alert{color:red;} .col-md-12{padding: 10px;} .col-md-2{padding: 5px;}']
})

export class ClockComponent implements OnInit {
  months = 0; weeks = 0; days = 0;
  hours = 0; mins = 0; secs = 0;
  event = "Event"; eventDate = "Jan 1, 2025"; alert ="";

  // constructor() {
  //   this.months = 0; this.weeks = 0; this.days = 0;
  //   this.hours = 0; this.mins = 0; this.secs = 0;
  //   this.event = ""; this.eventDate = ""; this.alert = "";
  // }

  ngOnInit() {
  }
  
  countDownTo(inputMonth:string, inputDay:string, inputYear:string): void{
    var toDateStr = `${inputMonth} ${inputDay}, ${inputYear}`;
    this.eventDate = toDateStr;

    var toDate = new Date(toDateStr).getTime(); // date object for countdown time

    // for calculations of time left
    var divMonth = 1000 * 60 * 60 * 24 * 7 * 4;
    var divWeek = 1000 * 60 * 60 * 24 * 7;
    var divDay = 1000 * 60 * 60 * 24;
    var divHour = 1000 * 60 * 60;
    var divMin = 1000 * 60;
    var divSec = 1000;

    for(var i=0; i<10000; i++) // clean up other setintervals sloppily per button click
    {
        window.clearInterval(i);
    }

    var timer = setInterval(() => { // updates timer for every second
      var fromDate = new Date().getTime();
      var millisecsBtwn = toDate - fromDate; // countdown in milliseconds
      
      if (millisecsBtwn <= 0) { // stop timer with clearInterval
        clearInterval(timer); // clear up this specific interval you know is passed right away
        this.alert = `${this.event} HAS PASSED. Select event on a different date.`;
        this.months = 0; this.weeks = 0; this.days = 0;
        this.hours = 0; this.mins = 0; this.secs = 0;
      }
      else {
        this.alert = "";
        this.months = Math.floor(millisecsBtwn / divMonth);
        this.weeks = Math.floor((millisecsBtwn % divMonth) / divWeek);
        this.days = Math.floor((millisecsBtwn % divWeek) / divDay);
        this.hours = Math.floor((millisecsBtwn % divDay) / divHour);
        this.mins = Math.floor((millisecsBtwn % divHour) / divMin);
        this.secs = Math.floor((millisecsBtwn % divMin)/divSec);
      }
      console.log(timer);
      console.log(this);
    }, 1000); // end 1 second interval
  } // end of countDownTo()

} // end of ClockComponent class
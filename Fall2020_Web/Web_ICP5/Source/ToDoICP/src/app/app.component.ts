import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})

export class AppComponent {
  // define list of items
  items= [];

  // Write code to push new item
  submitNewItem(itemStr: string): void {
    if(this.items.indexOf(itemStr) != -1){
      alert("This item already exists! Write a different item description.");
    } else{
      this.items.push(itemStr);
    }
    console.log(this.items);
  }

  // Write code to complete item
  completeItem(itemStr: string): void {
    let index: number = this.items.indexOf(itemStr);
    if(index != -1){
      let tbl = (<HTMLTableElement >document.getElementById("todo-list"));
      tbl.rows[index].style.backgroundColor = "orange";
      let item = tbl.rows[index].cells[1];
      let itemStr = item.innerHTML;
      item.innerHTML = itemStr.strike();
    }
    console.log(this.items);
  }

  // Write code to delete item
  deleteItem(itemStr:string): void {
    let index: number = this.items.indexOf(itemStr);
    if(index != -1){
      this.items.splice(index,1);
    }
    console.log(this.items);
  }
}
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-button-action',
  templateUrl: './button-action.component.html',
  styleUrls: ['./button-action.component.css'] 
})
export class ButtonActionComponent implements OnInit {
  @Output() 
  clickEvent = new EventEmitter<void>(); 

  constructor() {}

  ngOnInit(): void {}

  buttonNouveauClick(): void {
    this.clickEvent.emit(); 
  }
}

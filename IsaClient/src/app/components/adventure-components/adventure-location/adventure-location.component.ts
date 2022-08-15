import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';

@Component({
  selector: 'app-adventure-location',
  templateUrl: './adventure-location.component.html',
  styleUrls: ['./adventure-location.component.css']
})
export class AdventureLocationComponent implements OnInit {

  @Input()
  adventure! : Adventure;  
  
  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';

@Component({
  selector: 'app-adventure-info',
  templateUrl: './adventure-info.component.html',
  styleUrls: ['./adventure-info.component.css']
})
export class AdventureInfoComponent implements OnInit {

  @Input()
  adventure! : Adventure;
  
  constructor() { }

  ngOnInit(): void {
  }

}

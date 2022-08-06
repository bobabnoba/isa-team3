import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';

@Component({
  selector: 'app-adventure-services',
  templateUrl: './adventure-services.component.html',
  styleUrls: ['./adventure-services.component.css']
})
export class AdventureServicesComponent implements OnInit {
  
  @Input()
  adventure! : Adventure;
  
  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-services',
  templateUrl: './boat-services.component.html',
  styleUrls: ['./boat-services.component.css']
})
export class BoatServicesComponent implements OnInit {

  @Input()
  boat! : Boat;
  
  constructor() { }

  ngOnInit(): void {
  }

}

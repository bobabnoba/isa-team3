import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-services',
  templateUrl: './boat-services.component.html',
  styleUrls: ['./boat-services.component.css']
})
export class BoatServicesComponent implements OnInit {

  @Input()
  boat! : Boat;
  
  constructor() {
    this.boat = {} as Boat;
    this.boat.utilities = [] as Utility[];
   }

  ngOnInit(): void {
  }

}

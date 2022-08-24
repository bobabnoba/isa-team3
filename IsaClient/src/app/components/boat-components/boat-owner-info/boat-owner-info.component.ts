import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-owner-info',
  templateUrl: './boat-owner-info.component.html',
  styleUrls: ['./boat-owner-info.component.css']
})
export class BoatOwnerInfoComponent implements OnInit {

  @Input()
  boat! : Boat;  
  
  constructor() {
    this.boat = {} as Boat;
  
   }

  ngOnInit(): void {
  }

}

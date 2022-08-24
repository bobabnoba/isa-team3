import { Component, Input, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-location',
  templateUrl: './boat-location.component.html',
  styleUrls: ['./boat-location.component.css']
})
export class BoatLocationComponent implements OnInit {

  @Input()
  boat! : Boat;  
  
  constructor() { 
    this.boat = {} as Boat;
    this.boat.address = {} as IAddress;
  }

  ngOnInit(): void {
  }

}

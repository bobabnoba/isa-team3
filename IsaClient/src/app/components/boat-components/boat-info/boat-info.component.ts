import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-info',
  templateUrl: './boat-info.component.html',
  styleUrls: ['./boat-info.component.css']
})
export class BoatInfoComponent implements OnInit {

  @Input()
  boat! : Boat;
  
  constructor() {
    this.boat = {} as Boat;
  }

  ngOnInit(): void {
  }

}

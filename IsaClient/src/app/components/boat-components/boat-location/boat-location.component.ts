import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-location',
  templateUrl: './boat-location.component.html',
  styleUrls: ['./boat-location.component.css']
})
export class BoatLocationComponent implements OnInit {

  @Input()
  boat! : Boat;  
  
  constructor() { }

  ngOnInit(): void {
  }

}

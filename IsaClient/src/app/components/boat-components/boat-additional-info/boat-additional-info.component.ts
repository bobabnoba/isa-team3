import { Component, Input, OnInit } from '@angular/core';
import { Boat } from 'src/app/interfaces/boat';

@Component({
  selector: 'app-boat-additional-info',
  templateUrl: './boat-additional-info.component.html',
  styleUrls: ['./boat-additional-info.component.css']
})
export class BoatAdditionalInfoComponent implements OnInit {

  @Input()
  boat! : Boat;
  
  constructor() { }

  ngOnInit(): void {
  }

}

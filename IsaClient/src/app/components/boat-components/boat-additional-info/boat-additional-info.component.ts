import { Component, Input, OnInit } from '@angular/core';
import { FishingEquipment } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { Rule } from 'src/app/interfaces/rule';

@Component({
  selector: 'app-boat-additional-info',
  templateUrl: './boat-additional-info.component.html',
  styleUrls: ['./boat-additional-info.component.css']
})
export class BoatAdditionalInfoComponent implements OnInit {

  @Input()
  boat! : Boat;
  
  constructor() {
    this.boat = {} as Boat;
    this.boat.fishingEquipment = [] as FishingEquipment[];
    this.boat.codeOfConduct = [] as Rule[];
    this.boat.navigationTypes = [] as string[];
   }

  ngOnInit(): void {
  }

}

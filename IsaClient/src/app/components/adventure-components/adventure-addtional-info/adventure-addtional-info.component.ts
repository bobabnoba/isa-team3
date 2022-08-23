import { Component, Input, OnInit } from '@angular/core';
import { Adventure, FishingEquipment } from 'src/app/interfaces/adventure';

@Component({
  selector: 'app-adventure-addtional-info',
  templateUrl: './adventure-addtional-info.component.html',
  styleUrls: ['./adventure-addtional-info.component.css']
})
export class AdventureAddtionalInfoComponent implements OnInit {

  @Input()
  adventure! : Adventure;
  
  constructor() { 
    this.adventure = {} as Adventure;
    this.adventure.fishingEquipment = [] as FishingEquipment[];
  }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Adventure, Utility } from 'src/app/interfaces/adventure';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  @Input() adventureId! : string
  adventure! : Adventure;
  
  constructor(private _adventureService : AdventureService) { 
    this.adventure = {} as Adventure;
    this.adventure.utilities = [] as Utility[];
    this.adventure.specialOffers = [] as SpecialOffer[];
  }

  ngOnInit(): void {
    this._adventureService.getById(this.adventureId).subscribe(
      res => {
        this.adventure = res;
      }
    )
  }

}

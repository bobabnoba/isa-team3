import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { BoatService } from 'src/app/services/boat-service/boat.service';

@Component({
  selector: 'app-boat-profile',
  templateUrl: './boat-profile.component.html',
  styleUrls: ['./boat-profile.component.css']
})
export class BoatProfileComponent implements OnInit {

  @Input() boatId! : string
  boat! : Boat;
  
  constructor(private _boatService : BoatService) { 
    this.boat = {} as Boat;
    this.boat.utilities = [] as Utility[];
  }

  ngOnInit(): void {
    this._boatService.getById(this.boatId).subscribe(
      res => {
        this.boat = res;
      }
    )
  }

}

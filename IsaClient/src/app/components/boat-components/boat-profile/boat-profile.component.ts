import { Component, Input, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { ImageListItem } from 'src/app/interfaces/image-list-item';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-boat-profile',
  templateUrl: './boat-profile.component.html',
  styleUrls: ['./boat-profile.component.css']
})
export class BoatProfileComponent implements OnInit {

  @Input() boatId! : string
  boat! : Boat;
  baseUrl = environment.apiURL
  images : ImageListItem[] = [];

  constructor(private _boatService : BoatService) { 
    this.images = [] as ImageListItem[];
    this.boat = {} as Boat;
    this.boat.utilities = [] as Utility[];
    this.boat.specialOffers = [] as SpecialOffer[];
    this.boat.address = {} as IAddress;
    
  }

  ngOnInit(): void {
    this._boatService.getById(this.boatId).subscribe(
      res => {
        this.boat = res;
        this.boat.images.map( (image,index) => {
          if(index == 0) {
            this.images.push({src:this.baseUrl  + image, caption:image, act:'carousel-item active '});
          } else {
          this.images.push({src:this.baseUrl  + image, caption:image, act:'carousel-item '});
          }
        })
        
      }
    )
    
    console.log(this.images);
  }
}

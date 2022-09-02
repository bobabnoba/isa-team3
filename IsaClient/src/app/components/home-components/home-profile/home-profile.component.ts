import { Component, Input, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { Utility } from 'src/app/interfaces/adventure';
import { ImageListItem } from 'src/app/interfaces/image-list-item';
import { IOwnerInfo } from 'src/app/interfaces/owner-info';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-home-profile',
  templateUrl: './home-profile.component.html',
  styleUrls: ['./home-profile.component.css']
})
export class HomeProfileComponent implements OnInit {

  @Input() homeId! : string
  home! : VacationHome;
  baseUrl = environment.apiURL
  images : ImageListItem[] = [];

  constructor(private _homeService : HomeService) { 
    this.images = [] as ImageListItem[];
    this.home = {} as VacationHome;
    this.home.utilities = [] as Utility[];
    this.home.specialOffers = [] as SpecialOffer[];
    this.home.address = {} as IAddress;
    this.home.vacationHomeOwner = {} as IOwnerInfo;
    
  }

  ngOnInit(): void {
    this._homeService.getById(this.homeId).subscribe(
      res => {
        this.home = res;
        this.home.imageUrls.map( (image,index) => {
          if(index == 0) {
            this.images.push({src:this.baseUrl  + image, caption:image, act:'carousel-item active '});
          } else {
          this.images.push({src:this.baseUrl  + image, caption:image, act:'carousel-item '});
          }
        })
        
      }
    )
    console.log(this.home);
    console.log(this.images);
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/interfaces/adventure';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-boat-special-offer',
  templateUrl: './boat-special-offer.component.html',
  styleUrls: ['./boat-special-offer.component.css']
})
export class BoatSpecialOfferComponent implements OnInit {

  @Input()
  offer: SpecialOffer = {} as SpecialOffer;
  endsIn! : string;
  isClient : boolean = false;
  
  constructor(private _storageService : StorageService) {  this.offer.utilities = [] as Utility[]; }

  ngOnInit(): void {
    
    if(this._storageService.getRole() == "ROLE_CLIENT"){
      this.isClient = true;
    }

    let today = new Date();
    let endDate = new Date(this.offer.activeTo);

    let time = endDate.getTime() - today.getTime();
    let days = time / (1000* 3600 * 24);
    let hours = (time % (1000* 3600 * 24)) / (1000* 3600);
    let minutes = (time % (1000* 3600)) / (1000* 60);

    this.endsIn = Math.floor(days) + " days, " + Math.floor(hours) + " hours, " + Math.floor(minutes) + " minutes";
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }


}

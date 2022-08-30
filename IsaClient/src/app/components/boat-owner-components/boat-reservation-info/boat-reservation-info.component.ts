import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { Reservation } from 'src/app/interfaces/reservation';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-boat-reservation-info',
  templateUrl: './boat-reservation-info.component.html',
  styleUrls: ['./boat-reservation-info.component.css']
})
export class BoatReservationInfoComponent implements OnInit {

  reservation : Reservation = {} as Reservation;
  client : LoggedUser = {} as LoggedUser;
  utilities : Utility[] = [];
  resId : number;
  boat : Boat = {} as Boat;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _reservationService : ReservationService, private _boatService : BoatService)  {  
      this.resId = this.data.resId;
  }

  ngOnInit(): void {
      this._reservationService.getReservation(this.resId).subscribe(res => {
        this.reservation = res;
        this.client = res.client;
        this.utilities = res.utilities;
      })
      this._boatService.getBoatForReservation(this.resId).subscribe(boat => {
        this.boat = boat;
      })
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }

}

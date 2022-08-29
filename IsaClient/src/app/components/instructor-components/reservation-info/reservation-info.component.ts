import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Utility } from 'src/app/interfaces/adventure';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { Reservation } from 'src/app/interfaces/reservation';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-reservation-info',
  templateUrl: './reservation-info.component.html',
  styleUrls: ['./reservation-info.component.css']
})
export class ReservationInfoComponent implements OnInit {

  reservation : Reservation = {} as Reservation;
  client : LoggedUser = {} as LoggedUser;
  utilities : Utility[] = [];
  resId : number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _reservationService : ReservationService) {  
      this.resId = this.data.resId;
  }

  ngOnInit(): void {
      this._reservationService.getReservation(this.resId).subscribe(res => {
        this.reservation = res;
        this.client = res.client;
        this.utilities = res.utilities;
      })
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }
}

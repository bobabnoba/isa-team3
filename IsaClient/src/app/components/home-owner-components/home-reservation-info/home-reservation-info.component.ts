import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Utility } from 'src/app/interfaces/adventure';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { Reservation } from 'src/app/interfaces/reservation';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';

@Component({
  selector: 'app-home-reservation-info',
  templateUrl: './home-reservation-info.component.html',
  styleUrls: ['./home-reservation-info.component.css']
})
export class HomeReservationInfoComponent implements OnInit {

  reservation : Reservation = {} as Reservation;
  client : LoggedUser = {} as LoggedUser;
  utilities : Utility[] = [];
  resId : number;
  home : VacationHome = {} as VacationHome;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _reservationService : ReservationService, private _homeService : HomeService)  {  
      this.resId = this.data.resId;
  }

  ngOnInit(): void {
      this._reservationService.getReservation(this.resId).subscribe(res => {
        this.reservation = res;
        this.client = res.client;
        this.utilities = res.utilities;
      })
      this._homeService.getHomeForReservation(this.resId).subscribe(home => {
        this.home = home;
      })
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }


}

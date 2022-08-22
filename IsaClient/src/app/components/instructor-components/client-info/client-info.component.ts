import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent implements OnInit {

  client : LoggedUser = {} as LoggedUser;
  resId : number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _reservationService : ReservationService) {  
      this.resId = this.data.resId;
  }

  ngOnInit(): void {
      this._reservationService.getReservation(this.resId).subscribe(res => {
        this.client = res.client;
      })
  }

}

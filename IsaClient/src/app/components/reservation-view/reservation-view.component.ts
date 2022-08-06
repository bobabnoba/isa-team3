import { Component, Input, OnInit } from '@angular/core';
import { IAvailableReservations } from 'src/app/interfaces/vacation-house-profile';

@Component({
  selector: 'app-reservation-view',
  templateUrl: './reservation-view.component.html',
  styleUrls: ['./reservation-view.component.css']
})
export class ReservationViewComponent implements OnInit {
  @Input() 
  action!:IAvailableReservations
  constructor() {
  }

  ngOnInit(): void {
  }

}

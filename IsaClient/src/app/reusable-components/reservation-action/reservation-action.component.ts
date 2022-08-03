import { Component, Input, OnInit } from '@angular/core';
import { IAvailableReservations } from 'src/app/interfaces/vacation-house-profile';

@Component({
  selector: 'app-reservation-action',
  templateUrl: './reservation-action.component.html',
  styleUrls: ['./reservation-action.component.css']
})
export class ReservationActionComponent implements OnInit {
  @Input() 
  action!:IAvailableReservations
  constructor() {
  }

  ngOnInit(): void {
  }

}

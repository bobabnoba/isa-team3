import { Component, Input, OnInit } from '@angular/core';
import { SpecialOffer } from 'src/app/interfaces/special-offer';

@Component({
  selector: 'app-reservation-view',
  templateUrl: './reservation-view.component.html',
  styleUrls: ['./reservation-view.component.css']
})
export class ReservationViewComponent implements OnInit {
  @Input()
  offer!: SpecialOffer
  endsIn!: string;
  constructor() {
    console.log(this.offer);
    

  }

  ngOnInit(): void {
    let today = new Date();
    let endDate = new Date(this.offer.activeTo);

    let time = endDate.getTime() - today.getTime();
    let days = time / (1000 * 3600 * 24);
    let hours = (time % (1000 * 3600 * 24)) / (1000 * 3600);
    let minutes = (time % (1000 * 3600)) / (1000 * 60);

    this.endsIn = Math.floor(days) + " days, " + Math.floor(hours) + " hours, " + Math.floor(minutes) + " minutes";
  }

}

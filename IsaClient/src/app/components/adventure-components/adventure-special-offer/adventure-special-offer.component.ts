import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Utility } from 'src/app/interfaces/adventure';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-adventure-special-offer',
  templateUrl: './adventure-special-offer.component.html',
  styleUrls: ['./adventure-special-offer.component.css']
})
export class AdventureSpecialOfferComponent implements OnInit {

  @Input()
  offer: SpecialOffer = {} as SpecialOffer;
  @Input()
  adventureDuration : number = 0;
  endsIn!: string;
  isClient: boolean = false;
  newReservation: IReservation = {} as IReservation;

  constructor(
    private _rentalService: RentalService,
    private _snackBar: MatSnackBar,
    private _storageService: StorageService,
    private _datePipe: DatePipe
    ) {
    this.offer.utilities = [] as Utility[];
    this.newReservation.utilities = [];
  }

  ngOnInit(): void {

    if (this._storageService.getRole() == "ROLE_CLIENT") {
      this.isClient = true;
    }

    let today = new Date();
    let endDate = new Date(this.offer.activeTo);

    let time = endDate.getTime() - today.getTime();
    let days = time / (1000 * 3600 * 24);
    let hours = (time % (1000 * 3600 * 24)) / (1000 * 3600);
    let minutes = (time % (1000 * 3600)) / (1000 * 60);

    this.endsIn = Math.floor(days) + " days, " + Math.floor(hours) + " hours, " + Math.floor(minutes) + " minutes";
  }

  compareObjects(o1: any, o2: any) {
    if (o1.name == o2.name && o1.id == o2.id)
      return true;
    else return false
  }
  bookNow() {
    this.newReservation.endDate = this._datePipe.transform(new Date(this.offer.reservationStartDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.startDate = this._datePipe.transform(new Date(this.offer.reservationEndDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.guests = this.offer.guests;
    this.newReservation.price = this.offer.price;
    this.newReservation.utilities = this.offer.utilities;

    console.log(this.newReservation);
    
    
  }

}

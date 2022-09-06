import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Utility } from 'src/app/interfaces/adventure';
import { LoggedClient } from 'src/app/interfaces/logged-client';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { ClientService } from 'src/app/services/client-service/client.service';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-adventure-special-offer',
  templateUrl: './adventure-special-offer.component.html',
  styleUrls: ['./adventure-special-offer.component.css']
})
export class AdventureSpecialOfferComponent implements OnInit {

  @Input()
  adventureId!: number;
  @Input()
  rentalId!: number
  @Input()
  offer: SpecialOffer = {} as SpecialOffer;
  @Input()
  adventureDuration: number = 0;
  @Input()
  type: string = "";
  endsIn!: string;
  isClient: boolean = false;
  newReservation: IReservation = {} as IReservation;
  client: LoggedClient = {} as LoggedClient;
  displayDiscount = false;
  calculatedPrice: number = 0;
  showTotalPrice: boolean = false;

  constructor(
    private _rentalService: RentalService,
    private _snackBar: MatSnackBar,
    private _storageService: StorageService,
    private _datePipe: DatePipe,
    private _clientService: ClientService
  ) {
    this.offer.utilities = [] as Utility[];
    this.newReservation.utilities = [];
  }

  ngOnInit(): void {

    if (this._storageService.getRole() == "ROLE_CLIENT") {
      this.isClient = true;
      this._clientService.getClientInfo(this._storageService.getEmail()).subscribe(
        (data) => {
          this.client = data;
          if (this.client.rank.percentage == 0) {
            this.displayDiscount = false;

          }
          this.displayDiscount = true;
          this.calculatePrice();

        }
      );
    }

    let today = new Date();
    let endDate = new Date(this.offer.activeTo);

    let time = endDate.getTime() - today.getTime();
    let days = time / (1000 * 3600 * 24);
    let hours = (time % (1000 * 3600 * 24)) / (1000 * 3600);
    let minutes = (time % (1000 * 3600)) / (1000 * 60);

    this.endsIn = Math.floor(days) + " days, " + Math.floor(hours) + " hours, " + Math.floor(minutes) + " minutes";
  }
  calculatePrice() {
    let discount = 0;
    let originalPrice = this.offer.price
    if (this.displayDiscount) {
      discount = originalPrice * this.client.rank.percentage / 100
    }
    this.calculatedPrice = originalPrice - discount
    this.showTotalPrice = true;
  }

  compareObjects(o1: any, o2: any) {
    if (o1.name == o2.name && o1.id == o2.id)
      return true;
    else return false
  }
  bookNow() {
    this.newReservation.endDate = this._datePipe.transform(new Date(this.offer.reservationEndDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.startDate = this._datePipe.transform(new Date(this.offer.reservationStartDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.guests = this.offer.guests;
    this.newReservation.price = this.offer.price;
    this.newReservation.utilities = this.offer.utilities;

    console.log(this.newReservation);

    const reservation = {
      next: (res: any) => {

        this._snackBar.open('Reservation made successfully!', '',
          {
            duration: 3000,
            panelClass: ['snack-bar']
          });
        window.location.href = '/client/reservations'
      },
      error: (err: HttpErrorResponse) => {
        let message = 'Sorry :( it seems like we have a problem. Try again in few minutes!'

        if (err.status == 403) {
          message = 'You have been prevented from making reservations because you have more than 3 penalties. \n ' +
            ' Penalties are deleted at the start of each month. '
        } else if (err.status == 409) {
          message = 'You already have a reservation for this time period! \n '

        }

        this._snackBar.open(message, 'Close',
          {
            duration: 10000,
            panelClass: ['snack-bar']
          });
      }
    }
    if (this.type == 'adventure') {
      this._rentalService.rentAdventureSpecialOffer(this.newReservation, this.adventureId, this.offer.id, this._storageService.getEmail()).subscribe(reservation);
    } else if (this.type == 'home') {
      this._rentalService.rentHomeSpecialOffer(this.newReservation, this.rentalId, this.offer.id, this._storageService.getEmail()).subscribe(reservation);
    } else if (this.type == 'boat') {
      this._rentalService.rentBoatSpecialOffer(this.newReservation, this.rentalId, this.offer.id, this._storageService.getEmail()).subscribe(reservation);
    } else {
      this._snackBar.open('No type specified', 'Close', {
        duration: 3000
      })
    }
  }

}

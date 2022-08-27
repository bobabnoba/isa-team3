import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { ReservationType, SpecialOffer } from 'src/app/interfaces/special-offer';
import { BoatOwnerService } from 'src/app/services/boat-owner-service/boat-owner.service';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { SpecialOfferService } from 'src/app/services/special-offer-service/special-offer.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { transform } from 'typescript';

@Component({
  selector: 'app-add-boat-special-offer',
  templateUrl: './add-boat-special-offer.component.html',
  styleUrls: ['./add-boat-special-offer.component.css']
})
export class AddBoatSpecialOfferComponent implements OnInit {

  form = new FormControl();

  boats: Boat[] = [];
  existingServices: Utility[] = [];
  selectedBoat: Boat = {} as Boat;
  services: Utility[] = []

  activeFrom = new FormControl([Validators.required]);
  activeTo = new FormControl([Validators.required]);
  reservationStartDate = new FormControl([Validators.required]);
  numOfDays = new FormControl([Validators.required]);
  boat = new FormControl([Validators.required]);
  guests = new FormControl([Validators.required]);
  price = new FormControl([Validators.required]);
  servi = new FormControl();
  isCaptain = new FormControl();
  today = this._datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm')
  discount: number = 0;
  displayDiscount = false;

  constructor(private _offerService: SpecialOfferService, private _boatService: BoatService,
    private _storageService: StorageService, private _boatOwnerService: BoatOwnerService,
    private _snackBar: MatSnackBar, private _datePipe: DatePipe,
    private _dialogRef: MatDialogRef<AddBoatSpecialOfferComponent>) {
      this.numOfDays.setValue(1);
      this.isCaptain.setValue(false);
     }

  ngOnInit(): void {
    this._boatService.getAllByBoatOwner(this._storageService.getEmail()).subscribe(
      res => {
        this.boats = res;
      }
    )
    console.log(this.today)
  }

  updatePrice() {
    this.price.setValue(this.selectedBoat.pricePerDay * this.numOfDays.value);
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    this.price.setValue(this.price.value + utilitiesSum);
    this.showDiscount();
  }

  boatChosen() {
    this.price.setValue(this.selectedBoat.pricePerDay);
  }

  offerDateChosen() {
    let offerFrom = new Date(this.activeFrom.value);
    let offerTo = new Date(this.activeTo.value);

    if (offerTo < offerFrom) {
      this.activeTo.reset();
      this._snackBar.open('End date cannot be before start date!', '',
        { duration: 2000, panelClass: ['snack-bar'] }
      );
    }
  }

  resDateChosen() {

    let resDate = new Date(this.reservationStartDate.value);
    let resStartDate = resDate;
    let resEnd = new Date(this.reservationStartDate.value);
    let num : number = this.numOfDays.value ;
    let resEndDate = new Date(resEnd.getTime());
    console.log( this.numOfDays.value)
    resEndDate.setDate(resEndDate.getDate() + this.numOfDays.value);

    let offerTo = new Date(this.activeTo.value);
    if (resStartDate < offerTo) {
      this.reservationStartDate.reset();
      this._snackBar.open('Reservation cannot be made in time when offer is still active!', '',
        { duration: 2000, panelClass: ['snack-bar'] }
      );
    }
    console.log(resStartDate)
    console.log(resEndDate)

    this._boatService.checkBoatAvailability(
      this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
      this.selectedBoat.id).subscribe(
        res => {
          if (!res) {
            this.reservationStartDate.reset();
            this.numOfDays.reset();
            this._snackBar.open('Given date is not available! Please check boat calendar.', '',
              { duration: 3000, panelClass: ['snack-bar'] }
            );
          }
        }
      )

      //ako je vlasnik kapetan proveri da li je slobodan tada
   if(this.isCaptain.value){
    this._boatOwnerService.checkOwnerAvailability(
      this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._storageService.getEmail()).subscribe(
        res => {
          if (!res) {
            this.reservationStartDate.reset();
            this.numOfDays.reset();
            this._snackBar.open('Given date is not available! Please check your calendar.', '',
              { duration: 3000, panelClass: ['snack-bar'] }
            );
          }
        }
      )
   }

  }



  createOffer() {
    if (this.activeFrom.valid && this.activeTo.valid && this.boat.valid && this.reservationStartDate.valid
      && this.guests.valid && this.price.valid && this.numOfDays.valid) {
      this._offerService.createSpecialOffer(this.createOfferObj(), this.selectedBoat.id).subscribe(
        res => {
          this._snackBar.open('Offer created!', '',
            { duration: 3000, panelClass: ['snack-bar'] }
          );
          this._dialogRef.close();
        }
      );
    }

  }

  showDiscount() {
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    let totalPrice = this.selectedBoat.pricePerDay * this.numOfDays.value + utilitiesSum;
    this.discount = Math.round((totalPrice - this.price.value) * 100 / totalPrice);
    console.log(this.discount)
    if (this.discount != 0) {
      this.displayDiscount = true;
    } else {
      this.displayDiscount = false;
    }
  }
  getDaysBetween(dateStart: any, dateEnd: any): number {
    var diff = Math.abs(dateEnd.getTime() - dateStart.getTime());
    var diffDays = Math.ceil(diff / (1000 * 3600 * 24));
    return diffDays;
  }

  createOfferObj(): SpecialOffer {
    let resEnd = new Date(this.reservationStartDate.value);
    let resEndDate = new Date(resEnd.getTime() +  24 * 60 * 60 * 1000 - 24 * 60 * 60 * 1000);
    resEndDate.setDate(resEndDate.getDate() + this.numOfDays.value);
    let offer = {} as SpecialOffer;
    offer.discount = this.discount;
    offer.price = this.price.value;
    offer.activeFrom = this._datePipe.transform(new Date(this.activeFrom.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.activeTo = this._datePipe.transform(new Date(this.activeTo.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.reservationStartDate = this._datePipe.transform(new Date(this.reservationStartDate.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.reservationEndDate = this._datePipe.transform(resEndDate, 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    // offer.guests = this.numOfDays.value;
    offer.type = ReservationType.BOAT;
    offer.utilities = this.services;
    offer.isCaptain = this.isCaptain.value;
    return offer;
  } 

}


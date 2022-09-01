import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Utility } from 'src/app/interfaces/adventure';
import { ReservationType, SpecialOffer } from 'src/app/interfaces/special-offer';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { SpecialOfferService } from 'src/app/services/special-offer-service/special-offer.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';

@Component({
  selector: 'app-add-home-special-offer',
  templateUrl: './add-home-special-offer.component.html',
  styleUrls: ['./add-home-special-offer.component.css']
})
export class AddHomeSpecialOfferComponent implements OnInit {

  form = new FormControl();

  homes: VacationHome[] = [];
  existingServices: Utility[] = [];
  selectedHome: VacationHome = {} as VacationHome;
  services: Utility[] = []

  activeFrom = new FormControl([Validators.required]);
  activeTo = new FormControl([Validators.required]);
  reservationStartDate = new FormControl([Validators.required]);
  numOfDays = new FormControl([Validators.required]);
  home = new FormControl([Validators.required]);
  guests = new FormControl([Validators.required]);
  price = new FormControl([Validators.required]);
  servi = new FormControl();
  //isCaptain = new FormControl();
  today = this._datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm')
  discount: number = 0;
  displayDiscount = false;

  constructor(private _offerService: SpecialOfferService, private _homeService: HomeService,
    private _storageService: StorageService,
    private _snackBar: MatSnackBar, private _datePipe: DatePipe,
    private _dialogRef: MatDialogRef<AddHomeSpecialOfferComponent>) {
      this.numOfDays.setValue(1);
     }

  ngOnInit(): void {
    this._homeService.getAllByHomeOwner(this._storageService.getEmail()).subscribe(
      res => {
        this.homes = res;
      }
    )
  }

  updatePrice() {
    this.price.setValue(this.selectedHome.pricePerDay * this.numOfDays.value);
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    this.price.setValue(this.price.value + utilitiesSum);
    this.showDiscount();
  }

  homeChosen() {
    this.price.setValue(this.selectedHome.pricePerDay);
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

    let resDate = new Date(this.reservationStartDate.value) ;
    let resStartDate = resDate;
    resStartDate.setHours(0, 0, 0, 0);
    let resEnd = new Date(this.reservationStartDate.value);
    let num : number = this.numOfDays.value ;
    let resEndDate = new Date(resEnd.getTime());
    resEndDate.setHours(0, 0, 0, 0);
    resEndDate.setDate(resEndDate.getDate() + (this.numOfDays.value -1));

    let offerTo = new Date(this.activeTo.value);
    if (resStartDate < offerTo) {
      this.reservationStartDate.reset();
      this._snackBar.open('Reservation cannot be made in time when offer is still active!', '',
        { duration: 2000, panelClass: ['snack-bar'] }
      );
    }

    this._homeService.checkHomeAvailability(
      this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
      this.selectedHome.id).subscribe(
        res => {
          if (!res) {
            this.reservationStartDate.reset();
            this.numOfDays.reset();
            this._snackBar.open('HOME NOT AVAILABLE! Given date is not available! Please check home calendar.', '',
              { duration: 3000, panelClass: ['snack-bar'] }
            );
          }
        }
      )

      

  }



  createOffer() {
    if (this.activeFrom.valid && this.activeTo.valid && this.home.valid && this.reservationStartDate.valid
      && this.guests.valid && this.price.valid && this.numOfDays.valid) {
      this._offerService.createSpecialOffer(this.createOfferObj(), this.selectedHome.id).subscribe(
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
    let totalPrice = this.selectedHome.pricePerDay * this.numOfDays.value + utilitiesSum;
    this.discount = Math.round((totalPrice - this.price.value) * 100 / totalPrice);
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
    let resStart = new Date(this.reservationStartDate.value);
    let resEndDate = new Date(resStart.getTime() +  24 * 60 * 60 * 1000 - 24 * 60 * 60 * 1000);
    resEndDate.setDate(resEndDate.getDate() + (this.numOfDays.value -1));
    resStart.setHours(0, 0, 0, 0);
    resEndDate.setHours(0, 0, 0, 0);
    let offer = {} as SpecialOffer;
    offer.discount = this.discount;
    offer.price = this.price.value;
    offer.activeFrom = this._datePipe.transform(new Date(this.activeFrom.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.activeTo = this._datePipe.transform(new Date(this.activeTo.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.reservationStartDate = this._datePipe.transform(resStart, 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.reservationEndDate = this._datePipe.transform(resEndDate, 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.type = ReservationType.VACATION_HOME;
    offer.utilities = this.services;
    //offer.isCaptain = false;
    return offer;
  } 


}

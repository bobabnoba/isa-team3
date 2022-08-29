import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { addHours } from 'date-fns';
import { Adventure, Utility } from 'src/app/interfaces/adventure';
import { ReservationType, SpecialOffer } from 'src/app/interfaces/special-offer';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { SpecialOfferService } from 'src/app/services/special-offer-service/special-offer.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-add-special-offer',
  templateUrl: './add-special-offer.component.html',
  styleUrls: ['./add-special-offer.component.css'], 
  
})
export class AddSpecialOfferComponent implements OnInit {


  form = new FormControl();

  adventures : Adventure[] = [];
  existingServices : Utility[] = [];
  selectedAdventure : Adventure = {} as Adventure;
  services : Utility[] = []

  activeFrom = new FormControl([Validators.required]);
  activeTo = new FormControl([Validators.required]);
  reservationDate = new FormControl([Validators.required]);
  adventure = new FormControl([Validators.required]);
  guests =new FormControl([Validators.required]);
  price = new FormControl([Validators.required]);
  servi = new FormControl();
  today = this._datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm')
  discount : number = 0;  
  displayDiscount = false;

  constructor(private _offerService : SpecialOfferService, private _advService : AdventureService,
     private _storageService : StorageService, private _insService : InstructorService,
     private _snackBar : MatSnackBar, private _datePipe : DatePipe,
     private _dialogRef: MatDialogRef<AddSpecialOfferComponent>) { }

  ngOnInit(): void {
    this._advService.getAllByInstructor(this._storageService.getEmail()).subscribe(
      res => {
        this.adventures = res;
      }
    )
    console.log(this.today)
  }

  updatePrice(){
    this.price.setValue(this.selectedAdventure.pricePerDay * this.guests.value);
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    this.price.setValue(this.price.value + utilitiesSum);
    this.showDiscount();
  }

  advChosen() {
    this.guests.setValue(this.selectedAdventure.maxNumberOfParticipants)
    this.price.setValue(this.selectedAdventure.pricePerDay * this.selectedAdventure.maxNumberOfParticipants);
  }

  offerDateChosen(){
    let offerFrom = new Date(this.activeFrom.value);
    let offerTo = new Date(this.activeTo.value);

    if ( offerTo < offerFrom){
      this.activeTo.reset();
      this._snackBar.open('End date cannot be before start date!', '',
            { duration: 2000, panelClass: ['snack-bar'] }
      );
  }
}

  resDateChosen(){

    let resDate = new Date(this.reservationDate.value);
    let resStartDate = resDate;
    let resEndDate = addHours(resDate, this.selectedAdventure.durationInHours);

    let offerTo = new Date(this.activeTo.value);
  if(resStartDate < offerTo) {
    this.reservationDate.reset();
    this._snackBar.open('Reservation cannot be made in time when offer is still active!', '',
            { duration: 2000, panelClass: ['snack-bar'] }
          );
  }

  this._insService.checkAvailability(
    this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
     this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!, 
     this._storageService.getEmail()).subscribe(
    res => {
      if (!res) {
        this.reservationDate.reset();
        this._snackBar.open('Given date is not available! Please check your calendar.', '',
            { duration: 3000, panelClass: ['snack-bar'] }
        );
      }
    }
  )
  }

  createOffer(){
    if(this.activeFrom.valid && this.activeTo.valid && this.adventure.valid && this.reservationDate.valid
      && this.guests.valid && this.price.valid){
        this._offerService.createSpecialOffer(this.createOfferObj(), this.selectedAdventure.id).subscribe(
          res => {
            this._snackBar.open('Offer created!', '',
                { duration: 3000, panelClass: ['snack-bar'] }
            );
            this._dialogRef.close();
          }
        );
      }
  
  }

  showDiscount(){
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    let totalPrice = this.selectedAdventure.pricePerDay * this.guests.value + utilitiesSum;
    this.discount = Math.round((totalPrice - this.price.value) * 100 / totalPrice);
    if(this.discount > 0){
      this.displayDiscount = true;
    }else {
      this.displayDiscount = false;
    }
  }

  createOfferObj() : SpecialOffer {
    
    let offer = {} as SpecialOffer;
    offer.discount = this.discount;
    offer.price = this.price.value;
    offer.activeFrom = this._datePipe.transform(new Date(this.activeFrom.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.activeTo = this._datePipe.transform(new Date(this.activeTo.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.reservationStartDate = this._datePipe.transform(new Date(this.reservationDate.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.reservationEndDate = this._datePipe.transform(addHours(new Date(this.reservationDate.value), this.selectedAdventure.durationInHours), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    offer.guests = this.guests.value;
    offer.type = ReservationType.ADVENTURE;
    offer.utilities = this.services;
    offer.cancelingPercentage = this.selectedAdventure.cancelingPercentage;

    return offer;
  }

}

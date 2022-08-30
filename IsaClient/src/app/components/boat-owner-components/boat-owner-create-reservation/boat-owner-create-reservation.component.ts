import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { addHours } from 'date-fns';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { ReservationType } from 'src/app/interfaces/special-offer';
import { BoatOwnerService } from 'src/app/services/boat-owner-service/boat-owner.service';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { ClientService } from 'src/app/services/client-service/client.service';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { InstructorCreateReservationComponent } from '../../instructor-components/instructor-create-reservation/instructor-create-reservation.component';

@Component({
  selector: 'app-boat-owner-create-reservation',
  templateUrl: './boat-owner-create-reservation.component.html',
  styleUrls: ['./boat-owner-create-reservation.component.css']
})
export class BoatOwnerCreateReservationComponent implements OnInit {

  form = new FormControl();
  
  chosenClient : LoggedUser = {} as LoggedUser
  data! : any 
  noAvalilableClient : boolean = true;
  boats: Boat[] = [];
  existingServices: Utility[] = [];
  selectedBoat: Boat = {} as Boat;
  services: Utility[] = []

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

  constructor(@Inject(MAT_DIALOG_DATA) public passedData: any, private _clientService : ClientService,
     private _storageService : StorageService, private _insService : InstructorService,
     private _snackBar : MatSnackBar, private _datePipe : DatePipe, private _rentService : RentalService,
     private _dialogRef : MatDialogRef<InstructorCreateReservationComponent>,
     private _reservationService : ReservationService , private _boatService : BoatService, 
     private _boatOwnerService : BoatOwnerService) { 
      
      this.data = passedData;
      this.guests.setValue(passedData.guests);
      this.price.setValue(passedData.price * passedData.guests);
      this.existingServices = passedData.utilities;
      this.numOfDays.setValue(1);
      this.isCaptain.setValue(false);

     }




  ngOnInit(): void {
    this._reservationService.checkIfOngoingReservation(this.data.resId).subscribe(
      res =>{
        this.chosenClient = res;
        this.noAvalilableClient = false;
      }
    );
    this._boatService.getAllByBoatOwner(this._storageService.getEmail()).subscribe(
      res => {
        this.boats = res;
      }
    )
    
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
  resDateChosen() {

    let resDate = new Date(this.reservationStartDate.value) ;
    let resStartDate = resDate;
    resStartDate.setHours(0, 0, 0, 0);
    let resEnd = new Date(this.reservationStartDate.value);
    let num : number = this.numOfDays.value ;
    let resEndDate = new Date(resEnd.getTime());
    resEndDate.setHours(0, 0, 0, 0);
    resEndDate.setDate(resEndDate.getDate() + (this.numOfDays.value -1));


    this._boatService.checkBoatAvailability(
      this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
      this.selectedBoat.id).subscribe(
        res => {
          if (!res) {
            this.reservationStartDate.reset();
            this.numOfDays.reset();
            this._snackBar.open('BOAT NOT AVAILABLE! Given date is not available! Please check boat calendar.', '',
              { duration: 3000, panelClass: ['snack-bar'] }
            );
          }
        }
      )

   if(this.isCaptain.value){
    this._boatOwnerService.checkOwnerAvailability(
      this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._storageService.getEmail()).subscribe(
        res => {
          if (!res) {
            this.reservationStartDate.reset();
            this.numOfDays.reset();
            this._snackBar.open('YOU ARE NOT AVAILABLE! Given date is not available! Please check your calendar.', '',
              { duration: 3000, panelClass: ['snack-bar'] }
            );
          }
        }
      )
   }

   this._clientService.checkForOverlappingReservation(
          this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
          this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
          this.chosenClient.email
        ).subscribe(
          res => {
            if(res){
              this.reservationStartDate.reset();
            this._snackBar.open('Client is not available on given time period!', '',
                { duration: 3000, panelClass: ['snack-bar'] }
            );
            }
          }
        )


  }

  showDiscount() {
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    let totalPrice = this.selectedBoat.pricePerDay * this.numOfDays.value + utilitiesSum;
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


  create(){
    if( this.boat.valid && this.reservationStartDate.valid
      && this.guests.valid && this.price.valid && this.numOfDays.valid){ 

      this._rentService.rentBoat(this.createReservationObject(), this.selectedBoat.id, this.chosenClient.email).subscribe(
        res => {
          this._snackBar.open('Boat successfully booked!', '',
            { duration: 3000, panelClass: ['snack-bar'] }
          );
          this._dialogRef.close();
        },
        err => {
          this._snackBar.open('Sorry, something went wrong. Please try again later.', '',
            { duration: 3000, panelClass: ['snack-bar'] }
          );
          this._dialogRef.close();
        }
      )}
  }

  createReservationObject() : IReservation {
    let resStart = new Date(this.reservationStartDate.value);
    let resEndDate = new Date(resStart.getTime() +  24 * 60 * 60 * 1000 - 24 * 60 * 60 * 1000);
    resEndDate.setDate(resEndDate.getDate() + (this.numOfDays.value -1));
    resStart.setHours(0, 0, 0, 0);
    resEndDate.setHours(0, 0, 0, 0);
    let res = {} as IReservation;
    res.startDate = this._datePipe.transform(resStart, 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    res.endDate = this._datePipe.transform(resEndDate, 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
    res.price = this.price.value;
    res.utilities = this.services;
    res.type = ReservationType.BOAT;
    res.utilities = this.services;
    res.isCaptain = this.isCaptain.value;
      
      
    return res;
  }


}

import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Utility } from 'src/app/interfaces/adventure';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { ReservationType } from 'src/app/interfaces/special-offer';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { ClientService } from 'src/app/services/client-service/client.service';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { VacationHomeOwnerService } from 'src/app/services/vacation-home-owner-service/vacation-home-owner.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { InstructorCreateReservationComponent } from '../../instructor-components/instructor-create-reservation/instructor-create-reservation.component';

@Component({
  selector: 'app-home-owner-create-reservation',
  templateUrl: './home-owner-create-reservation.component.html',
  styleUrls: ['./home-owner-create-reservation.component.css']
})
export class HomeOwnerCreateReservationComponent implements OnInit {

  form = new FormControl();
  
  chosenClient : LoggedUser = {} as LoggedUser
  data! : any 
  noAvalilableClient : boolean = true;
  homes: VacationHome[] = [];
  existingServices: Utility[] = [];
  selectedHome: VacationHome = {} as VacationHome;
  services: Utility[] = []

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
  priceWDiscount = 0;

  constructor(@Inject(MAT_DIALOG_DATA) public passedData: any, private _clientService : ClientService,
     private _storageService : StorageService,
     private _snackBar : MatSnackBar, private _datePipe : DatePipe, private _rentService : RentalService,
     private _dialogRef : MatDialogRef<InstructorCreateReservationComponent>,
     private _reservationService : ReservationService , private _homeService : HomeService, 
     private _homeOwnerService : VacationHomeOwnerService) { 
      
      this.data = passedData;
      this.guests.setValue(passedData.guests);
      this.price.setValue(passedData.price * passedData.guests);
      this.existingServices = passedData.utilities;
      this.numOfDays.setValue(1);
      //this.isCaptain.setValue(false);

     }




  ngOnInit(): void {
    this._reservationService.checkIfOngoingReservation(this.data.resId).subscribe(
      res =>{
        this.chosenClient = res;
        this.noAvalilableClient = false;
      }
    );
    this._homeService.getAllByHomeOwner(this._storageService.getEmail()).subscribe(
      res => {
        this.homes = res;
      }
    )
    
  }
  calcDiscount(){
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    let totalPrice = this.selectedHome.pricePerDay * this.numOfDays.value + utilitiesSum;
    this.priceWDiscount = Math.round(totalPrice - (totalPrice * this.chosenClient.rank.percentage / 100));
    return this.priceWDiscount;
  }
  updatePrice() {
    this.price.setValue(this.selectedHome.pricePerDay * this.numOfDays.value);
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    this.price.setValue(this.price.value + utilitiesSum);
    //this.showDiscount();
    this.calcDiscount();
  }

  homeChosen() {
    this.price.setValue(this.selectedHome.pricePerDay);
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


  create(){
    if( this.home.valid && this.reservationStartDate.valid
      && this.guests.valid && this.price.valid && this.numOfDays.valid){ 

      this._rentService.ownerRentHome(this.createReservationObject(), this.selectedHome.id, this.chosenClient.email).subscribe(
        res => {
          this._snackBar.open('Home successfully booked!', '',
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
    res.price = this.priceWDiscount === 0 ? this.price.value : this.priceWDiscount;
    res.utilities = this.services;
    res.type = ReservationType.VACATION_HOME;
    res.utilities = this.services;
    //res.ownerCaptain = false;
    return res;
  }

}

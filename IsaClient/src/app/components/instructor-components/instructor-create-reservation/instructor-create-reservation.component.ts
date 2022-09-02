import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { addHours } from 'date-fns';
import {  Utility } from 'src/app/interfaces/adventure';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { ClientService } from 'src/app/services/client-service/client.service';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { IReservation } from 'src/app/interfaces/new-reservation';

@Component({
  selector: 'app-instructor-create-reservation',
  templateUrl: './instructor-create-reservation.component.html',
  styleUrls: ['./instructor-create-reservation.component.css']
})
export class InstructorCreateReservationComponent implements OnInit {

 
  form = new FormControl();
  
  chosenClient : LoggedUser = {} as LoggedUser
  existingServices : Utility[] = [];
  services : Utility[] = []
  data! : any 
  noAvalilableClient : boolean = true;

  resFrom = new FormControl([Validators.required]);
  client = new FormControl([Validators.required]);
  guests =new FormControl([Validators.required]);
  price = new FormControl([Validators.required]);
  priceWDiscount = 0;
  servi = new FormControl();
  today = this._datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm')


  constructor(@Inject(MAT_DIALOG_DATA) public passedData: any, private _clientService : ClientService,
     private _storageService : StorageService, private _insService : InstructorService,
     private _snackBar : MatSnackBar, private _datePipe : DatePipe, private _rentService : RentalService,
     private _dialogRef : MatDialogRef<InstructorCreateReservationComponent>) { 
      
      this.data = passedData;
      this.guests.setValue(passedData.guests);
      this.price.setValue(passedData.price * passedData.guests);
      this.existingServices = passedData.utilities;

     }


     calcDiscount(){
      let utilitiesSum = 0;
      this.services.forEach(s => { utilitiesSum += s.price });
      let totalPrice = this.data.price * this.guests.value + utilitiesSum;
      this.priceWDiscount = Math.round(totalPrice - (totalPrice * this.chosenClient.rank.percentage / 100));
      return this.priceWDiscount;
    }

  ngOnInit(): void {
    this._insService.getOngoingResClient(this._storageService.getEmail()).subscribe(
      res =>{
        this.chosenClient = res;
        this.noAvalilableClient = false;
        this.priceWDiscount =  Math.round((this.data.price * this.data.guests) - ((this.data.price * this.data.guests) * res.rank.percentage / 100));

      }
    );
    
  }

  updatePrice(){
    this.price.setValue(this.data.price * this.guests.value);
    let utilitiesSum = 0;
    this.services.forEach(s => { utilitiesSum += s.price });
    this.price.setValue(this.price.value + utilitiesSum);
    this.calcDiscount();
  }

  clientChosen(){}

  resDateChosen(){

    let resDate = new Date(this.resFrom.value);
    let resStartDate = resDate;
    let resEndDate = addHours(resDate, this.data.duration );

    this._clientService.checkForOverlappingReservation(
      this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
      this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!,
      this.chosenClient.email
    ).subscribe(
      res => {
        if(!res){
          this.resFrom.reset();
        this._snackBar.open('Client is not available on given time period!', '',
            { duration: 3000, panelClass: ['snack-bar'] }
        );
        }
      }
    )

  //   this._insService.checkForOverlappingReservation(
  //     this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
  //     this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!, 
  //     this._storageService.getEmail()).subscribe(
  //   res => {
  //     if (!res) {
  //       this.resFrom.reset();
  //       this._snackBar.open('You are not available on given time period! Please check your calendar.', '',
  //           { duration: 3000, panelClass: ['snack-bar'] }
  //       );
  //     }
  //   }
  // )

  this._insService.checkAvailability(
    this._datePipe.transform(resStartDate, 'yyyy-MM-dd HH:mm:ss')!,
    this._datePipe.transform(resEndDate, 'yyyy-MM-dd HH:mm:ss')!, 
    this._storageService.getEmail()).subscribe(
  res => {
    if (!res) {
      this.resFrom.reset();
      this._snackBar.open('You are not available on given time period! Please check your calendar.', '',
          { duration: 3000, panelClass: ['snack-bar'] }
      );
    }
  }
)
  }

  create(){
    if(this.resFrom.valid && this.guests.valid && this.price.valid){ 

      this._rentService.rentAdventureInstructor(this.createReservationObject(), this.data.adventureId, this.chosenClient.email).subscribe(
        res => {
          this._snackBar.open('Adventure successfully booked!', '',
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
    let res = {} as IReservation;
      res.startDate = this._datePipe.transform(new Date(this.resFrom.value), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
      res.endDate = this._datePipe.transform(addHours(new Date(this.resFrom.value), this.data.duration), 'yyyy-MM-ddTHH:mm:ss.SSSZ')!;
      res.price = this.priceWDiscount === 0 ? this.price.value : this.priceWDiscount;
      res.guests = this.guests.value;
      res.duration = this.data.duration;
      res.utilities = this.services;
      res.cancelingPercentage = this.data.cancelingPercentage;
      
    return res;
  }

}

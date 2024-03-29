import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Boat } from 'src/app/interfaces/boat';
import { BoatAndHomeAvailability } from 'src/app/interfaces/boat-availability';
import { BoatService } from 'src/app/services/boat-service/boat.service';

@Component({
  selector: 'app-boat-availability',
  templateUrl: './boat-availability.component.html',
  styleUrls: ['./boat-availability.component.css']
})
export class BoatAvailabilityComponent implements OnInit {

  boat! : Boat;
  
  aFormGroup!: FormGroup;
  newAv! : any;
  todayDate:Date = new Date();
  constructor(private _formBuilder: FormBuilder, private _boatService : BoatService,
               private _snackBar : MatSnackBar, private _router : Router) {
                this.boat = {} as Boat;
                }

  ngOnInit(): void {
    this._boatService.getById(this._router.url.substring(26)).subscribe( (data : any) => {
      this.boat = data;
      });
    this.aFormGroup = this._formBuilder.group({
      startDate: new FormControl('', [
        Validators.required,
      ]),
      endDate: new FormControl('', [
        Validators.required,
      ]),
    });
  }

  addNew(){
    
    const observer = {
      next: (data : any) => { 
        if(data == false){
          this.addAvailability();
        }else{
          this._snackBar.open('Reservation exists for choosen period.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
        );
        }
       },
      error: (err : any) => { 
        this._snackBar.open('Error.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
        ); },
      complete: () => {  }
    };

    this._boatService.checkIfReservationOverlapsAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      boatId : this.boat.id
    }).subscribe( observer );
    
    
  }
 
  addAvailability(){
    const observer = {
      next: (data : any) => { 
        this.newAv = data;
        this._snackBar.open('New availability period successfully added.', '',
        { duration: 3000, panelClass: ['snack-bar'] });
       },
      error: (err : any) => { 
        this._snackBar.open('Error adding new availability period.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
        ); },
      complete: () => {  }
    };

    this._boatService.addAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      boatId : this.boat.id
    }).subscribe( observer );
  }

  removePeriod(){
    const observer = {
      next: (data : any) => { 
        this.newAv = data;
        this._snackBar.open('Availability period successfully removed.', '',
        { duration: 3000, panelClass: ['snack-bar'] });
       },
      error: (err : any) => { 
        this._snackBar.open('Error removing availability period.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
        ); },
      complete: () => {  }
    };

    this._boatService.removeAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      boatId : this.boat.id
    }).subscribe( observer );

  }

}
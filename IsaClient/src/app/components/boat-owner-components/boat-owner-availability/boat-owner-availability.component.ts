import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BoatOwnerService } from 'src/app/services/boat-owner-service/boat-owner.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-boat-owner-availability',
  templateUrl: './boat-owner-availability.component.html',
  styleUrls: ['./boat-owner-availability.component.css']
})
export class BoatOwnerAvailabilityComponent implements OnInit {

  aFormGroup!: FormGroup;
  newAv! : any;
  todayDate:Date = new Date();
  constructor(private _formBuilder: FormBuilder, private _boatOwnerService :  BoatOwnerService,
    private _snackBar : MatSnackBar, private _storage : StorageService) { }

  ngOnInit(): void {
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

    this._boatOwnerService.checkIfReservationOverlapsAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      email : this._storage.getEmail()
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

    this._boatOwnerService.addAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      email : this._storage.getEmail()
    }).subscribe( observer );
  }
 
}

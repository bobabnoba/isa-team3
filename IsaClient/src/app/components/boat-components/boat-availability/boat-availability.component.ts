import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Boat } from 'src/app/interfaces/boat';
import { BoatAvailability } from 'src/app/interfaces/boat-availability';
import { BoatService } from 'src/app/services/boat-service/boat.service';

@Component({
  selector: 'app-boat-availability',
  templateUrl: './boat-availability.component.html',
  styleUrls: ['./boat-availability.component.css']
})
export class BoatAvailabilityComponent implements OnInit {

  //@Input()
  boat! : Boat;

  //boatId! : string;
  
  aFormGroup!: FormGroup;
  newAv! : any;
  constructor(private _formBuilder: FormBuilder, private _boatService : BoatService,
               private _snackBar : MatSnackBar, private _router : Router) {
                this.boat = {} as Boat;
                this.boat.availability = [] as BoatAvailability[];
                }

  ngOnInit(): void {
    this._boatService.getById(this._router.url.substring(26)).subscribe( (data : Boat) => {
      this.boat = data;
      });
    //this.boatId = this._router.url.substring(17);
    console.log(this.boat);
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
    console.log(this.aFormGroup.value.startDate);
    console.log(this.aFormGroup.value.endDate);

    const observer = {
      next: (data : any) => { 
        console.log(data);
        this.newAv = data;
        this._snackBar.open('New availability period successfully added.', '',
        { duration: 3000, panelClass: ['snack-bar'] });
       },
      error: (err : any) => { 
        console.log(err);
        this._snackBar.open('Error adding new availability period.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
        ); },
      complete: () => { console.log('done'); }
    };

    this._boatService.addAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      boatId : this.boat.id
    }).subscribe( observer );
  }
 

}
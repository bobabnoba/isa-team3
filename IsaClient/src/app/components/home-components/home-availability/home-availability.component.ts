import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';

@Component({
  selector: 'app-home-availability',
  templateUrl: './home-availability.component.html',
  styleUrls: ['./home-availability.component.css']
})
export class HomeAvailabilityComponent implements OnInit {

  home! : VacationHome;
  
  aFormGroup!: FormGroup;
  newAv! : any;
  todayDate:Date = new Date();
  constructor(private _formBuilder: FormBuilder, private _homeService : HomeService,
               private _snackBar : MatSnackBar, private _router : Router) {
                this.home = {} as VacationHome;
                }

  ngOnInit(): void {
    this._homeService.getById(this._router.url.substring(26)).subscribe( (data : any) => {
      this.home = data;
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

    this._homeService.checkIfReservationOverlapsAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      homeId : this.home.id
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

    this._homeService.addAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      homeId : this.home.id
    }).subscribe( observer );
  }
  }



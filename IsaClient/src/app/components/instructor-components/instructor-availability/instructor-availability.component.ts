import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-instructor-availability',
  templateUrl: './instructor-availability.component.html',
  styleUrls: ['./instructor-availability.component.css']
})
export class InstructorAvailabilityComponent implements OnInit {
  aFormGroup!: FormGroup;
  newAv! : any;
  constructor(private _formBuilder: FormBuilder, private _instructorService : InstructorService,
              private _storageService:  StorageService, private _snackBar : MatSnackBar) { }

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

    this._instructorService.addAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      instructorEmail : this._storageService.getEmail()
    }).subscribe( data => {
      this.newAv = data;
      this._snackBar.open('New availability period successfully added.', '',
      { duration: 3000, panelClass: ['snack-bar'] }
    );
    this.aFormGroup.reset();
    })
  }

  delete(){

    this._instructorService.deleteAvailability( {
      startDate : this.aFormGroup.value.startDate,
      endDate : this.aFormGroup.value.endDate,
      instructorEmail : this._storageService.getEmail()
    }).subscribe( data => {
      this.newAv = data;
      this._snackBar.open('You are unavailable for the given period. Enjoy your time off!', '',
      { duration: 3000, panelClass: ['snack-bar'] }
    );
    this.aFormGroup.reset();
    })
  }
 
}

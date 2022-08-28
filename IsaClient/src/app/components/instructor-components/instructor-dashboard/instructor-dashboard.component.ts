import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-instructor-dashboard',
  templateUrl: './instructor-dashboard.component.html',
  styleUrls: ['./instructor-dashboard.component.css']
})
export class InstructorDashboardComponent implements OnInit {

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
 
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-boat-owner-availability',
  templateUrl: './boat-owner-availability.component.html',
  styleUrls: ['./boat-owner-availability.component.css']
})
export class BoatOwnerAvailabilityComponent implements OnInit {

  aFormGroup!: FormGroup;
  newAv! : any;
  constructor(private _formBuilder: FormBuilder) { }

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

  addAvailability(){
  }

 
}

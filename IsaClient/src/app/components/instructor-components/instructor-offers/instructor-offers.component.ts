import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddSpecialOfferComponent } from '../add-special-offer/add-special-offer.component';

@Component({
  selector: 'app-instructor-offers',
  templateUrl: './instructor-offers.component.html',
  styleUrls: ['./instructor-offers.component.css']
})
export class InstructorOffersComponent implements OnInit {

  form = this._formBuilder.group({
    adventure: ['', Validators.required],
    activeUntil : [''],
    reservationDate : ['', Validators.required],
    guests:  ['', Validators.required],
    price:  ['', Validators.required],

  })
  

  constructor(private _matDialog : MatDialog, private _formBuilder : FormBuilder) { }

  ngOnInit(): void {
  }

  newOffer(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    dialogConfig.height = '650px';
    const dialogRef = this._matDialog.open(AddSpecialOfferComponent, dialogConfig);
  }

}

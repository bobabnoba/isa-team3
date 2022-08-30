import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-boat-owner-report',
  templateUrl: './boat-owner-report.component.html',
  styleUrls: ['./boat-owner-report.component.css']
})
export class BoatOwnerReportComponent implements OnInit {

  createForm!: FormGroup;
  client! : LoggedUser;
  resId! : number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _formBuilder: FormBuilder, public dialogRef : MatDialogRef<BoatOwnerReportComponent>,
    private _reservationService: ReservationService) { 
      this.resId = this.data.resId;
      this.client = {} as LoggedUser;
    this.createForm = this._formBuilder.group({
      response: new FormControl('', Validators.required),
      shownUp: new FormControl(false, Validators.required),
      suggestPenalty: new FormControl(false, Validators.required)
    })
   }

  ngOnInit(): void {
    this._reservationService.getReservation(this.resId).subscribe(res => {
      this.client = res.client;
    })
  }

  submit() {
    if(this.createForm.valid){
      this.dialogRef.close({ event: "Responded", data: {
        response: this.createForm.value.response,
        shownUp: this.createForm.value.shownUp,
        clientEmail : this.client.email,
        suggestPenalty : this.createForm.value.suggestPenalty
      }});
    }
  }

}

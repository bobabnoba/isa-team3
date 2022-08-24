import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-instructor-report',
  templateUrl: './instructor-report.component.html',
  styleUrls: ['./instructor-report.component.css']
})
export class InstructorReportComponent implements OnInit {

  createForm!: FormGroup;
  client! : LoggedUser;
  resId! : number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _formBuilder: FormBuilder, public dialogRef : MatDialogRef<InstructorReportComponent>,
    private _reservationService: ReservationService) { 
      this.resId = this.data.resId;
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

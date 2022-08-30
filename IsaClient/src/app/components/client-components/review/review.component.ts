import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ReviewService } from 'src/app/services/review-service/review.service';
import { InstructorReportComponent } from '../../instructor-components/instructor-report/instructor-report.component';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  createForm!: FormGroup;
  resId!: number;
  rentalRating: number = 0;
  instructorRating: number = 0;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private _formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<InstructorReportComponent>,
    private _snackBar: MatSnackBar) {
    this.resId = this.data.resId;
    this.createForm = this._formBuilder.group({
      response: new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {
  }

  submit() {
    if (!this.createForm.valid || this.instructorRating == 0 ||
      this.rentalRating == 0) {
        this._snackBar.open('You must fill in all the fields!', '', {
          duration: 3000
        });
      return;
    }
    console.log(this.resId);
    console.log(this.rentalRating);
    console.log(this.instructorRating);
    console.log(this.createForm.value.response);
    this.dialogRef.close({ event: "Responded", data: {
      comment: this.createForm.value.response,
      rentalRating: this.rentalRating,
      instructorRating: this.instructorRating,
      reservationId: this.resId
    }});


  }
  getRentalRating(rating: number) {
    this.rentalRating = rating;
  }
  getInstructorRating(rating: number) {
    this.instructorRating = rating
  }

}

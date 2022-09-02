import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IComplaint } from 'src/app/interfaces/complaint';
import { InstructorReportComponent } from '../../instructor-components/instructor-report/instructor-report.component';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  createForm!: FormGroup;
  resId!: number;
  resData: IComplaint = {} as IComplaint;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private _formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<InstructorReportComponent>,
  ) {
    this.resId = this.data.resId;
    this.createForm = this._formBuilder.group({
      complaint: new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {
  }

  submit() {
    if (!this.createForm.valid) {
      console.log(this.createForm.valid);
      
      return;
    }

    this.resData.complaint = this.createForm.value.complaint
    this.resData.reservationId = this.resId

    this.dialogRef.close({
      event: "Responded", data: this.resData
    });


  }



}

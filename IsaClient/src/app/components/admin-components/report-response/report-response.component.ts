import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-report-response',
  templateUrl: './report-response.component.html',
  styleUrls: ['./report-response.component.css']
})
export class ReportResponseComponent implements OnInit {
  
  createForm!: FormGroup;
  shownUp! : boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _formBuilder: FormBuilder, public dialogRef : MatDialogRef<ReportResponseComponent>) {
    this.shownUp = data.shownUp;
    this.createForm = this._formBuilder.group({
      response: new FormControl('', Validators.required),
      penalty: new FormControl(!data.shownUp, Validators.required)
    })
   }

  ngOnInit(): void {
  }

  submit() {
    if(this.createForm.valid){
      this.dialogRef.close({ event: "Responded", data: {
        response: this.createForm.value.response,
        penalty: this.createForm.value.penalty
      }});
    }
  }

}

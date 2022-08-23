import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-report-response',
  templateUrl: './report-response.component.html',
  styleUrls: ['./report-response.component.css']
})
export class ReportResponseComponent implements OnInit {
  
  createForm!: FormGroup;

  constructor(private _formBuilder: FormBuilder, public dialogRef : MatDialogRef<ReportResponseComponent>) {
    this.createForm = this._formBuilder.group({
      response: new FormControl('', Validators.required),
      penalty: new FormControl(false, Validators.required)
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

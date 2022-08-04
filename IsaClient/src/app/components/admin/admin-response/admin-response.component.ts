import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-response',
  templateUrl: './admin-response.component.html',
  styleUrls: ['./admin-response.component.css']
})
export class AdminResponseComponent implements OnInit {
 
  createForm!: FormGroup;

  constructor(private _formBuilder: FormBuilder, public dialogRef : MatDialogRef<AdminResponseComponent>) {
    this.createForm = this._formBuilder.group({
      response: new FormControl('', Validators.required)
    })
   }

  ngOnInit(): void {
  }

  submit() {
    if(this.createForm.valid){
      this.dialogRef.close({ event: "Responded", data: this.createForm.value.response });
    }
  }

}

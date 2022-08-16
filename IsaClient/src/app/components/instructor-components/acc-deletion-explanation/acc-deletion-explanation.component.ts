import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-acc-deletion-explanation',
  templateUrl: './acc-deletion-explanation.component.html',
  styleUrls: ['./acc-deletion-explanation.component.css']
})
export class AccDeletionExplanationComponent implements OnInit {

  
  createForm!: FormGroup;

  constructor(private _formBuilder: FormBuilder, public dialogRef : MatDialogRef<AccDeletionExplanationComponent>) {
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

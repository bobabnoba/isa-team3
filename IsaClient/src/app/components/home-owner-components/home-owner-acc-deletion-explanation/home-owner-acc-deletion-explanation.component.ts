import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { AccDeletionExplanationComponent } from '../../instructor-components/acc-deletion-explanation/acc-deletion-explanation.component';

@Component({
  selector: 'app-home-owner-acc-deletion-explanation',
  templateUrl: './home-owner-acc-deletion-explanation.component.html',
  styleUrls: ['./home-owner-acc-deletion-explanation.component.css']
})
export class HomeOwnerAccDeletionExplanationComponent implements OnInit {

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

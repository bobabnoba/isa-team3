import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IDeleteAccountRequest } from 'src/app/interfaces/delete-account-request';
import { DeleteAccountService } from 'src/app/services/delete-account.service';

@Component({
  selector: 'app-delete-account-form',
  templateUrl: './delete-account-form.component.html',
  styleUrls: ['./delete-account-form.component.css']
})
export class DeleteAccountFormComponent implements OnInit {
  newRequest : IDeleteAccountRequest;
  text!: string;
  constructor(
    public _deleteAccountService : DeleteAccountService,
    public dialogRef: MatDialogRef<DeleteAccountFormComponent>,
    private _snackBar: MatSnackBar
    ) {
      this.newRequest = {} as IDeleteAccountRequest;
     }

  ngOnInit(): void {
  }

  doTextareaValueChange(ev: any) {
    this.text = ev.target.value;
  }
  submitRequest(): void {
     if (this.text && this.text != '') {
       this.newRequest.request = this.text;
       this._deleteAccountService.sendRequest(this.newRequest).subscribe();
       this._snackBar.open(
         'Your feedback has been successfully submitted.',
         'Dismiss'
       );

       this.dialogRef.close(true);
     }
  }

}

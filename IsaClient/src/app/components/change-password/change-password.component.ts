import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';


@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changePass!: FormGroup;

  constructor( private _formBuilder: FormBuilder, private _service: UserService,
     private _snackBar : MatSnackBar, private _dialogRef : MatDialogRef<ChangePasswordComponent>,
     private _storage : StorageService) { }

  ngOnInit(): void {
    this.changePass = this._formBuilder.group({
      oldPass: new FormControl('', [Validators.required]),
      newPass: new FormControl('', [Validators.required]),
      reenter:  new FormControl('', [Validators.required]),
    });
  }

  confirm(){

    if(this.changePass.value.newPass !== this.changePass.value.reenter){
      this._snackBar.open('Passwords do not match.', '', { duration: 6000, panelClass: ['snack-bar']});
      return;
    }

    if(this.changePass.valid){

      const changePassObserver = {
        next: () => {
          this._snackBar.open("Password successfully changed.", "", 
          {duration: 2000,
            panelClass:['snack-bar']
          });
          this._dialogRef.close();
        },
        error: (err: HttpErrorResponse) => {
          this._snackBar.open("Wrong password. Please try again.", "", 
          {duration: 2000,
            panelClass:['snack-bar']
          });
        },
      };
      this._service.changePassword(
        {
          oldPassword : this.changePass.value.oldPass,
          newPassword : this.changePass.value.newPass
        }, this._storage.getEmail()
      ).subscribe(changePassObserver);
    }
  }

}

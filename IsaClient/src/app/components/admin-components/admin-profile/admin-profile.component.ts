import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  user! : LoggedUser;
  topbarInfo : string = "";
  updateMode : boolean = false;

  constructor(private _userService : UserService, private _storageService : StorageService,
              private _snackBar : MatSnackBar, private _matDialog: MatDialog,
              private _deleteAccountService : DeleteAccountService) { }

  ngOnInit(): void {
    this._userService.getUserInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.user = data;
        this.topbarInfo = this.user.firstName + " " + this.user.lastName;
      }
    );
  }
  
  doSth() {
    if (this.updateMode) {
      this._userService.updateUser(this.user).subscribe(
        res => {
          this._snackBar.open('Profile info succesfully updated', '',
          {duration : 3000,panelClass: ['snack-bar']}
        );
        },
        err => {
          this._snackBar.open(err.error.message, '',
          {duration : 3000,panelClass: ['snack-bar']}
        );
        }
      )
    }
    this.updateMode = !this.updateMode
  }

}

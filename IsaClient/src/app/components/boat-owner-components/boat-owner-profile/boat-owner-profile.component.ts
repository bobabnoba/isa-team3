import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';
import { BoatOwnerAccDeletionExplanationComponent } from '../boat-owner-acc-deletion-explanation/boat-owner-acc-deletion-explanation.component';

@Component({
  selector: 'app-boat-owner-profile',
  templateUrl: './boat-owner-profile.component.html',
  styleUrls: ['./boat-owner-profile.component.css']
})
export class BoatOwnerProfileComponent implements OnInit {

  topbarInfo : string = "";
  user! : LoggedUser;
  updateMode : boolean = false;
  
  constructor(private _userService : UserService, private _storageService : StorageService,
    private _snackBar : MatSnackBar, private _matDialog : MatDialog,
    private _deleteAccountService : DeleteAccountService) { 
      this.user = {} as LoggedUser;
      this.user.address = {} as IAddress;
    }

  ngOnInit(): void {
    this._userService.getUserInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.user = data;
        this.topbarInfo = this.user.firstName + " " + this.user.lastName;
      }
    );
  }

  deleteAccount(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(BoatOwnerAccDeletionExplanationComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        let request = {
          email : this._storageService.getEmail(),
          explanation : res.data
        } as DeleteAccoutRequest;
        this._deleteAccountService.createDeletionRequest(request).subscribe(
          res => {
            this._snackBar.open('Your request has been sent to admins for approval.', '',
              {duration : 3000, panelClass: ['snack-bar']}
            );
          }
        );
      }
    })
  }

  doSth() {
    if (this.updateMode) {
      this._userService.updateUser(this.user).subscribe(
        res => {
          this._snackBar.open('Profile info succesfully updated!', '',
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

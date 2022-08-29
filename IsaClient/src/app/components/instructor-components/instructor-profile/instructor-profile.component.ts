import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { IRank } from 'src/app/interfaces/rank';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';
import { ChangePasswordComponent } from '../../change-password/change-password.component';
import { AccDeletionExplanationComponent } from '../acc-deletion-explanation/acc-deletion-explanation.component';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.css']
})
export class InstructorProfileComponent implements OnInit {

  topbarInfo : string = "";
  user! : LoggedUser;
  updateMode : boolean = false;
  rank! : string;
  
  constructor(private _userService : UserService, private _storageService : StorageService,
    private _snackBar : MatSnackBar, private _matDialog : MatDialog,
    private _deleteAccountService : DeleteAccountService) { }

  
  ngOnInit(): void {
    this._userService.getUserInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.user = data;
        this.rank = this.user.rank.name.split('_')[0];
        this.topbarInfo = this.user.firstName + " " + this.user.lastName;
      }
    );
  }

  info(){
    this._snackBar.open("You are a " + this.rank + " advertiser, which means you have " + this.user.rank.percentage + "%  of"
    + " the reservation price!", "", {
    duration: 3000,
    panelClass: ['snack-bar']
  });
  }

  deleteAccount(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(AccDeletionExplanationComponent, dialogConfig);

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

  changePassword(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '500px';
    dialogConfig.height = '400px';
    this._matDialog.open(ChangePasswordComponent, dialogConfig);
  }

}

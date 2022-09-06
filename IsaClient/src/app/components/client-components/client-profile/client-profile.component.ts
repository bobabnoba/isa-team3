import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { LoggedClient } from 'src/app/interfaces/logged-client';
import { ClientService } from 'src/app/services/client-service/client.service';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';
import { ChangePasswordComponent } from '../../change-password/change-password.component';
import { AccDeletionExplanationComponent } from '../../instructor-components/acc-deletion-explanation/acc-deletion-explanation.component';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  user!: LoggedClient;
  updateMode: boolean = false;
  rank!: string;

  constructor(
    private _clientService: ClientService,
    private _storageService: StorageService,
    private _snackBar: MatSnackBar,
    private _userService: UserService,
    private _matDialog: MatDialog,
    private _deleteAccountService: DeleteAccountService
  ) {
    this._clientService.getClientInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.user = data;
        this.user.points = Math.round(data.points*10)/10
        this.rank = this.user.rank.name.split('_')[0];
      }
    );
  }

  ngOnInit(): void {

  }

  doSth() {
    if (this.updateMode) {
      this._userService.updateUser(this.user).subscribe(
        res => {
          this._snackBar.open('Profile info succesfully updated', '',
            { duration: 3000, panelClass: ['snack-bar'] }
          );
        },
        err => {
          this._snackBar.open(err.error.message, '',
            { duration: 3000, panelClass: ['snack-bar'] }
          );
        }
      )
    }
    this.updateMode = !this.updateMode
  }
  info() {
    this._snackBar.open("You are a " + this.rank + " user, which means you have " + this.user.rank.percentage + "%  discount"
      + " on every reservation !", "", {
      duration: 3000,
    });
  }

  deleteAccount() {

    this._userService.hasIncomingReservations(this.user.id, 'ROLE_CLIENT').subscribe(
      res => {
        console.log(res);

        if (res != true) {
          this.sendDeleteRequest();
        } else {
          this._snackBar.open('You can\'t delete your profile while you have upcoming events!', '',
            { duration: 3000, panelClass: ['snack-bar'] }
          );
        }
      },
      err => {
        this._snackBar.open('Sorry it seems like we have a problem , try again later :( ', 'Close',
          { duration: 3000, panelClass: ['snack-bar'] }
        );
      }
    )
  }
  sendDeleteRequest() {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(AccDeletionExplanationComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        let request = {
          email: this._storageService.getEmail(),
          explanation: res.data
        } as DeleteAccoutRequest;
        this._deleteAccountService.createDeletionRequest(request).subscribe(
          res => {
            this._snackBar.open('Your request has been sent to admins for approval.', '',
              { duration: 3000, panelClass: ['snack-bar'] }
            );
          }
        );
      }
    })
  }



  changePassword() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '500px';
    dialogConfig.height = '400px';
    this._matDialog.open(ChangePasswordComponent, dialogConfig);
  }
}

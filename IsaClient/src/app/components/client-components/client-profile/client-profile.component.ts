import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  user!: LoggedUser;
  updateMode: boolean = false;
  rank!: string;

  constructor(private _userService: UserService, private _storageService: StorageService,
    private _snackBar: MatSnackBar,
  ) {
    this._userService.getUserInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.user = data;
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
}

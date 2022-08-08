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

  constructor(private _userService: UserService, private _storageService: StorageService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this._userService.getUserInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.user = data;
      }
    );
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

}

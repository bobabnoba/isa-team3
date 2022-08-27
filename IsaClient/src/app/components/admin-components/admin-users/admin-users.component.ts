import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user-service/user.service';
import { AddAdminComponent } from '../add-admin/add-admin.component';
import { LoyaltyProgramComponent } from '../loyalty-program/loyalty-program.component';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  constructor(private _matDialog : MatDialog, private _snackBar : MatSnackBar, 
              ) { }

  ngOnInit(): void {
  }

  openLoyaltyProgram() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '900px';
    dialogConfig.height = '530px';
    this._matDialog.open(LoyaltyProgramComponent, dialogConfig);
  }

  addNewAdmin(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '700px';
    dialogConfig.height = '550px';
    this._matDialog.open(AddAdminComponent, dialogConfig);
  }

}

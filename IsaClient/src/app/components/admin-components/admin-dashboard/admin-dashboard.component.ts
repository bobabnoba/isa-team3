import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/services/admin-service/admin.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { ChangePasswordComponent } from '../../change-password/change-password.component';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  constructor(private _adminService : AdminService, private _storage : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._adminService.checkStatus(this._storage.getEmail()).subscribe(
      res => {
        if(res) {
          this.openChangePasswordDialog();
        }
      }
    )
  }

  openChangePasswordDialog(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '500px';
    dialogConfig.height = '400px';
    this._matDialog.open(ChangePasswordComponent, dialogConfig);

    this._snackBar.open('On your first login, you must change your password.', '',
            { duration: 6000, panelClass: ['snack-bar'], verticalPosition: 'top' });
}

}

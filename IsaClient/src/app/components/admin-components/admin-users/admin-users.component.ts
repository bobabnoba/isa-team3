import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { UserInfo } from 'src/app/interfaces/user-info';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { UserService } from 'src/app/services/user-service/user.service';
import { AddAdminComponent } from '../add-admin/add-admin.component';
import { LoyaltyProgramComponent } from '../loyalty-program/loyalty-program.component';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  displayedColumns: string[] = ['role', 'firstName', 'lastName', 'email', 'points', 'rank', 'delete'];
  dataSource = new MatTableDataSource<UserInfo>();
  headAdmin : UserInfo = {} as UserInfo;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _userService : UserService, private _matDialog : MatDialog,
              private _snackBar : MatSnackBar, public storage : StorageService) { }

  ngOnInit(): void {

    this._userService.getHeadAdmin().subscribe(
      res => {
        this.headAdmin = res;
        this._userService.getAllUserInfo().subscribe(
          res => {
            res = res.filter(user => user.email != this.storage.getEmail());
            res.forEach(u => {
              if(u.email == this.headAdmin.email){
                u.headAdmin = true
              }
            })
            this.dataSource = new MatTableDataSource<UserInfo>(res)
            this.dataSource.paginator = this.paginator;
          }
        );
      }
    )
    
    
  }
  
  roleIcon(user : UserInfo) : string {

    if(user.role == "ROLE_ADMIN") { return 'fa-solid fa-gears'}
    else if(user.role == "ROLE_CLIENT") {return 'fa-solid fa-user'}
    else if(user.role == "ROLE_INSTRUCTOR") { return 'fa-solid fa-fish'}
    else if(user.role == "ROLE_BOAT_OWNER"){ return 'fa-solid fa-anchor' }
    else return 'fa-solid fa-house-chimney ';
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  delete(user : UserInfo) {
    
    this._userService.hasIncomingReservations(user.id, user.role).subscribe(
      res => {
        if(!res){
          this._userService.deleteUser(user.id).subscribe(
            () => {
              this.updateTable(user);
              this._snackBar.open("User deleted.", "", {duration: 2000, panelClass: ['snack-bar']});
            }
          );
        }
        else {
          this._snackBar.open("User has incoming reservations, therefore cannot be deleted.", "", {duration: 4000, panelClass: ['snack-bar']});

        }
      }
    )
    
  }

  updateTable(user : UserInfo) {
    let idx = this.dataSource.data.indexOf(user);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
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
    const dialogRef = this._matDialog.open(AddAdminComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if(res){
          this.dataSource.data.push(res.data.admin);
          this.dataSource._updateChangeSubscription();
        }
      }
    );
  }
}

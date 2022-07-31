import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table'
import { RegistrationRequest } from 'src/app/interfaces/registration-request';
import { AdminService } from 'src/app/services/admin-service/admin.service';
import { AdminResponseComponent } from '../../admin-response/admin-response.component';

@Component({
  selector: 'app-admin-registration-requests',
  templateUrl: './admin-registration-requests.component.html',
  styleUrls: ['./admin-registration-requests.component.css']
})
export class AdminRegistrationRequestsComponent implements OnInit, AfterViewInit  {

  displayedColumns: string[] = ['type', 'motivation', 'email', 'respond'];
  dataSource = new MatTableDataSource<RegistrationRequest>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _adminService : AdminService, private _matDialog : MatDialog,
              private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._adminService.getAllUnhandledRegistrationRequests().subscribe(
      res => {
        this.dataSource = new MatTableDataSource<RegistrationRequest>(res)
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  approve(request : RegistrationRequest) {
    request.approved = true;
    this._adminService.handleRegistrationRequest(request).subscribe(
      () => {
        this.updateTable(request);
        this._snackBar.open('Registration request accepted.', '',
          {duration : 3000, panelClass: ['snack-bar']}
        );
      }
    );
    }

  reject(request : RegistrationRequest){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(AdminResponseComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        request.response = res.data;
        request.approved = false;
        this._adminService.handleRegistrationRequest(request).subscribe(
          () => {
            this.updateTable(request);
            this._snackBar.open('Registration request denied.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        );
      }
    })
  }

  updateTable(request : RegistrationRequest) {
    let idx = this.dataSource.data.indexOf(request);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

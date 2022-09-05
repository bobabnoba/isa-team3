import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { AdminResponseComponent } from '../admin-response/admin-response.component';

@Component({
  selector: 'app-admin-delete-requests',
  templateUrl: './admin-delete-requests.component.html',
  styleUrls: ['./admin-delete-requests.component.css']
})
export class AdminDeleteRequestsComponent implements OnInit, AfterViewInit  {

  displayedColumns: string[] = [ 'email', 'explanation', 'respond'];
  dataSource = new MatTableDataSource<DeleteAccoutRequest>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _deleteService : DeleteAccountService, private _matDialog : MatDialog,
              private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._deleteService.getAllUprocessedRequests().subscribe(
      res => {
        this.dataSource = new MatTableDataSource<DeleteAccoutRequest>(res)
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  approve(request : DeleteAccoutRequest) {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(AdminResponseComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        request.adminResponse = res.data;
        request.approved = true;
        this._deleteService.processRequest(request.id, request).subscribe(
          () => {
            this.updateTable(request);
            this._snackBar.open('Account deletion request accepted.', '',
              {duration : 3000, panelClass: ['snack-bar']}
            );
          }, 
          () => {
            this._snackBar.open('This request is currently being processed by another admin.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        );
      }
    })
    }

  reject(request : DeleteAccoutRequest){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(AdminResponseComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        request.adminResponse = res.data;
        request.approved = false;
        this._deleteService.processRequest(request.id, request).subscribe(
          () => {
            this.updateTable(request);
            this._snackBar.open('Account deletion request denied.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          },
          () => {
            this.updateTable(request);
            this._snackBar.open('This request is currently being processed by another admin.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        );
      }
    })
  }

  updateTable(request : DeleteAccoutRequest) {
    let idx = this.dataSource.data.indexOf(request);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

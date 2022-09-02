import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AdminComplaint } from 'src/app/interfaces/admin-complaint';
import { ComplaintService } from 'src/app/services/complaint-service/complaint.service';
import { AdminResponseComponent } from '../admin-response/admin-response.component';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

 
  displayedColumns: string[] = ['type', 'clientEmail', 'complaint', 'ownerEmail', 'respond'];
  dataSource = new MatTableDataSource<AdminComplaint>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _complaintService : ComplaintService, private _matDialog : MatDialog,
              private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._complaintService.getAllPending().subscribe(
      res => {
        res.forEach(r => {
          r.reservationType.replace('_', ' ');
        })
        this.dataSource = new MatTableDataSource<AdminComplaint>(res)
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  respond(complaint : AdminComplaint) {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    const dialogRef = this._matDialog.open(AdminResponseComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        this._complaintService.respondToComplaint(complaint.id, res.data).subscribe(
          () => {
            this.updateTable(complaint);
            this._snackBar.open('Complaint review submitted.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          },
          () => {
            this._snackBar.open("Couldn't send email responses. Try again later!", '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        );
      }
    }) 
  }
  
  updateTable(complaint : AdminComplaint) {
    let idx = this.dataSource.data.indexOf(complaint);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

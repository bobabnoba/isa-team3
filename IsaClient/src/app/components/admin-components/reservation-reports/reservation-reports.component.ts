import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Report } from 'src/app/interfaces/report';
import { ReportResponse } from 'src/app/interfaces/report-response';
import { ReportService } from 'src/app/services/report-service/report.service';
import { UserService } from 'src/app/services/user-service/user.service';
import { AdminResponseComponent } from '../admin-response/admin-response.component';
import { ReportResponseComponent } from '../report-response/report-response.component';


@Component({
  selector: 'app-reservation-reports',
  templateUrl: './reservation-reports.component.html',
  styleUrls: ['./reservation-reports.component.css']
})
export class ReservationReportsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['type', 'ownerEmail', 'clientEmail', 'clientShowedUp', 'comment', 'penaltySuggested', 'respond'];
  dataSource = new MatTableDataSource<Report>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _userService : UserService, private _matDialog : MatDialog,
              private _snackBar : MatSnackBar, private _reportService : ReportService) { }

  ngOnInit(): void {
    this._reportService.getAllUprocessedReports().subscribe(
      res => {
        let ids : number[] = [];
        res.forEach( r => {
  
          if(r.type === "AdventureReservationReport"){
            r.type = "ADVENTURE"
          } else if (r.type === "BoatReservationReport"){
            r.type = "BOAT"
          } else {
            r.type = "VACATION HOME"
          }

          ids.push(r.instructorId); ids.push(r.boatOwnerId); ids.push(r.homeOwnerId);
          let idx = ids.filter(i => i!== null)
          if(idx.length !== 0) {
            this._userService.getUserById(idx[0]).subscribe(
              user => {
                r.ownerEmail = user.email;
              }
            );
          }
        })
        this.dataSource = new MatTableDataSource<Report>(res)
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  respond(report : Report) {
    let myData = {
      shownUp : report.clientShowedUp
     }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    dialogConfig.data = myData;
    const dialogRef = this._matDialog.open(ReportResponseComponent, dialogConfig);

    dialogRef.afterClosed().subscribe({
      next: (res) => {
        let response : ReportResponse = {
          reportId : report.id,
          response : res.data.response,
          ownerEmail : report.ownerEmail,
          clientEmail : report.clientEmail,
          penalty : res.data.penalty
        } as ReportResponse;

        this._reportService.adminReview(response).subscribe(
          () => {
            this.updateTable(report);
            this._snackBar.open('Reservation report reviewed.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          },
          (err) => {
            this._snackBar.open("Couldn't send email responses. Try again later!", '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        );
      }
    })
    
  }


  updateTable(report : Report) {
    let idx = this.dataSource.data.indexOf(report);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Report } from 'src/app/interfaces/report';
import { ReportService } from 'src/app/services/report-service/report.service';
import { UserService } from 'src/app/services/user-service/user.service';


@Component({
  selector: 'app-reservation-reports',
  templateUrl: './reservation-reports.component.html',
  styleUrls: ['./reservation-reports.component.css']
})
export class ReservationReportsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['type', 'ownerEmail', 'clientEmail', 'comment', 'clientShowedUp', 'respond'];
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

  approve(report : Report) {
   
    }


  updateTable(report : Report) {
    let idx = this.dataSource.data.indexOf(report);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

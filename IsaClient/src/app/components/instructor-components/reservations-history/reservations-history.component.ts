import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { Report } from 'src/app/interfaces/report';
import { Reservation } from 'src/app/interfaces/reservation';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { ReportService } from 'src/app/services/report-service/report.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { InstructorReportComponent } from '../instructor-report/instructor-report.component';
import { ReservationInfoComponent } from '../reservation-info/reservation-info.component';

@Component({
  selector: 'app-reservations-history',
  templateUrl: './reservations-history.component.html',
  styleUrls: ['./reservations-history.component.css']
})
export class ReservationsHistoryComponent implements OnInit {
  displayedColumns: string[] = ['startDate', 'duration', 'guests', 'price', 'moreInfo', 'report'];
  dataSource = new MatTableDataSource<AdventureReservation>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  searchText : string = "";

  constructor(private _instructorService: InstructorService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar,
              private _reportService : ReportService) { }

  ngOnInit(): void {
    this._instructorService.getInstructorReservationsHistory(this._storageService.getEmail()).subscribe(
      res => {
        this.dataSource = new MatTableDataSource<AdventureReservation>(res)
        this.dataSource.paginator = this.paginator;
      }
    )
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  
  handleMe(searchText : string){
    this.searchText = searchText;
  }

  showMoreInfo(reservation : AdventureReservation){
    let myData = {
      resId : reservation.id
     }
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = false;
     dialogConfig.id = 'modal-component';
     dialogConfig.width = '570px';
     dialogConfig.height = '330px';
     dialogConfig.data = myData;
     this._matDialog.open(ReservationInfoComponent, dialogConfig);
 
   }

   writeReport(reservation : AdventureReservation){
    let myData = {
      resId : reservation.id
     }
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = false;
     dialogConfig.id = 'modal-component';
     dialogConfig.data = myData;
     const dialogRef = this._matDialog.open(InstructorReportComponent, dialogConfig);
     dialogRef.afterClosed().subscribe({
      next: (res) => {
        let report : Report = {
          comment : res.data.response,
          clientShowedUp : res.data.shownUp,
          ownerEmail : this._storageService.getEmail(),
          clientEmail : res.data.clientEmail,
          penaltySuggested : res.data.suggestPenalty,
          type : "ADVENTURE"
        } as Report;
        this._reportService.sendReport(reservation.id, report).subscribe(
          () => {
            this.updateTable(reservation);
            reservation.report = report;
            this._snackBar.open('Reservation report successfully submitted.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        )
      }
    })
   }

   updateTable(reservation : AdventureReservation) {
    let idx = this.dataSource.data.indexOf(reservation);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }

}

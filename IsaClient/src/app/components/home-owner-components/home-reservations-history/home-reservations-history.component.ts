import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { BoatReservation } from 'src/app/interfaces/boat-reservation';
import { Report } from 'src/app/interfaces/report';
import { ReportService } from 'src/app/services/report-service/report.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { VacationHomeOwnerService } from 'src/app/services/vacation-home-owner-service/vacation-home-owner.service';
import { BoatOwnerReportComponent } from '../../boat-owner-components/boat-owner-report/boat-owner-report.component';
import { BoatReservationInfoComponent } from '../../boat-owner-components/boat-reservation-info/boat-reservation-info.component';

@Component({
  selector: 'app-home-reservations-history',
  templateUrl: './home-reservations-history.component.html',
  styleUrls: ['./home-reservations-history.component.css']
})
export class HomeReservationsHistoryComponent implements OnInit {

  displayedColumns: string[] = ['startDate', 'duration', 'price', 'moreInfo', 'report'];
  dataSource = new MatTableDataSource<BoatReservation>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  searchText : string = "";

  constructor(private _homeOwnerService: VacationHomeOwnerService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar,
              private _reportService : ReportService) { }

  ngOnInit(): void {
    this._homeOwnerService.getHomeOwnerReservationsHistory(this._storageService.getEmail()).subscribe(
      res => {
        this.dataSource = new MatTableDataSource<BoatReservation>(res)
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

  showMoreInfo(reservation : BoatReservation){
    let myData = {
      resId : reservation.id
     }
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = false;
     dialogConfig.id = 'modal-component';
     dialogConfig.width = '570px';
     dialogConfig.height = '330px';
     dialogConfig.data = myData;
     this._matDialog.open(BoatReservationInfoComponent, dialogConfig);
 
   }

   writeReport(reservation : BoatReservation){
    let myData = {
      resId : reservation.id
     }
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = false;
     dialogConfig.id = 'modal-component';
     dialogConfig.data = myData;
     const dialogRef = this._matDialog.open(BoatOwnerReportComponent, dialogConfig);
     dialogRef.afterClosed().subscribe({
      next: (res) => {
        let report : Report = {
          comment : res.data.response,
          clientShowedUp : res.data.shownUp,
          ownerEmail : this._storageService.getEmail(),
          clientEmail : res.data.clientEmail,
          penaltySuggested : res.data.suggestPenalty,
          type : "VACATION_HOME",
        } as Report;
        this._reportService.sendReport(reservation.id, report).subscribe(
          () => {
            reservation.report = report;
            this.dataSource._updateChangeSubscription();
            this._snackBar.open('Reservation report successfully submitted.', '',
            {duration : 3000, panelClass: ['snack-bar']}
        );
          }
        )
      }
    })
   }

   updateTable(reservation : BoatReservation) {
    let idx = this.dataSource.data.indexOf(reservation);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }

}

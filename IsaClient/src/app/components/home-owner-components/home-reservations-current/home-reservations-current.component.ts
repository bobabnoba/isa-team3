import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { BoatReservation } from 'src/app/interfaces/boat-reservation';
import { ReportService } from 'src/app/services/report-service/report.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { VacationHomeOwnerService } from 'src/app/services/vacation-home-owner-service/vacation-home-owner.service';
import { BoatReservationInfoComponent } from '../../boat-owner-components/boat-reservation-info/boat-reservation-info.component';
import { HomeOwnerCreateReservationComponent } from '../home-owner-create-reservation/home-owner-create-reservation.component';

@Component({
  selector: 'app-home-reservations-current',
  templateUrl: './home-reservations-current.component.html',
  styleUrls: ['./home-reservations-current.component.css']
})
export class HomeReservationsCurrentComponent implements OnInit {

  displayedColumns: string[] = ['startDate', 'duration', 'guests', 'price', 'moreInfo', 'report'];
  dataSource = new MatTableDataSource<BoatReservation>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  searchText : string = "";

  constructor(private _homeOwnerService: VacationHomeOwnerService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar,
              private _reportService : ReportService) { }

  ngOnInit(): void {
    this._homeOwnerService.getHomeOwnerCurrentReservations(this._storageService.getEmail()).subscribe(
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

   createNewReservation(reservation : BoatReservation){
    let myData = {
      resId : reservation.id
     }
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = false;
     dialogConfig.id = 'modal-component';
     dialogConfig.data = myData;
     const dialogRef = this._matDialog.open(HomeOwnerCreateReservationComponent, dialogConfig);
     
   }

   updateTable(reservation : BoatReservation) {
    let idx = this.dataSource.data.indexOf(reservation);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }

}

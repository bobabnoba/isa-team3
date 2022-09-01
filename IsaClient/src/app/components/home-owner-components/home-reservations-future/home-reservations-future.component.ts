import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { BoatReservation } from 'src/app/interfaces/boat-reservation';
import { Reservation } from 'src/app/interfaces/reservation';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { VacationHomeOwnerService } from 'src/app/services/vacation-home-owner-service/vacation-home-owner.service';
import { BoatReservationInfoComponent } from '../../boat-owner-components/boat-reservation-info/boat-reservation-info.component';

@Component({
  selector: 'app-home-reservations-future',
  templateUrl: './home-reservations-future.component.html',
  styleUrls: ['./home-reservations-future.component.css']
})
export class HomeReservationsFutureComponent implements OnInit {

  displayedColumns: string[] = ['startDate', 'duration', 'guests', 'price', 'moreInfo'];
  dataSource = new MatTableDataSource<BoatReservation>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  searchText : string = "";

  constructor(private _homeOwnerService: VacationHomeOwnerService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._homeOwnerService.getHomeOwnerUpcomingReservations(this._storageService.getEmail()).subscribe(
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

  showMoreInfo(reservation : Reservation){
    let myData = {
      resId : reservation.id
     }
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = false;
     dialogConfig.id = 'modal-component';
     dialogConfig.width = '570px';
     dialogConfig.height = '340px';
     dialogConfig.data = myData;
     dialogConfig.panelClass = 'custom-modalbox';
     this._matDialog.open(BoatReservationInfoComponent, dialogConfig);
 
   }
}

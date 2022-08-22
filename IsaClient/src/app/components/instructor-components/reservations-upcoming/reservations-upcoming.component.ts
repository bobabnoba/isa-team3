import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { Reservation } from 'src/app/interfaces/reservation';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { ReservationInfoComponent } from '../reservation-info/reservation-info.component';

@Component({
  selector: 'app-reservations-upcoming',
  templateUrl: './reservations-upcoming.component.html',
  styleUrls: ['./reservations-upcoming.component.css']
})
export class ReservationsUpcomingComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['startDate', 'duration', 'guests', 'price', 'moreInfo'];
  dataSource = new MatTableDataSource<AdventureReservation>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  searchText : string = "";

  constructor(private _instructorService: InstructorService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._instructorService.getInstructorUpcomingReservations(this._storageService.getEmail()).subscribe(
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

  showMoreInfo(reservation : Reservation){
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

}

import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-client-reservations',
  templateUrl: './client-reservations.component.html',
  styleUrls: ['./client-reservations.component.css']
})
export class ClientReservationsComponent implements AfterViewInit {
  displayedColumns: string[] = ['position', 'type', 'startDate', 'endDate', 'people', 'price', 'info', 'cancel'];
  allItems!: IReservation[];
  dataSource = new MatTableDataSource<IReservation>();
  userEmail: string = ''

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private _service: ReservationService,
    private _snackBar: MatSnackBar,
    private _storageService: StorageService) {
    this.userEmail = _storageService.getEmail();

    const getUpcomingReservations = {
      next: (res: any) => {
        console.log(res);
        this.dataSource.data = res;

      },
      error: (err: HttpErrorResponse) => {

      }
    }

    this._service.getUpcomingReservations(this.userEmail).subscribe(getUpcomingReservations);
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;

  }

  CancelReservation(id: number) {
    const cancelReservation = {
      next: (res: any) => {
        this.dataSource.data = res;
      },
      error: (err: HttpErrorResponse) => {

        this._snackBar.open('Reservation can be canceled at least 3 days before the start date. ', '',
          {
            duration: 3000,
            panelClass: ['snack-bar']
          });

      }
    }

    this._service.cancelUpcomingReservation(id, this.userEmail).subscribe(cancelReservation);

  }

  MoreInfo(type: string, id: number) {

    if (type == 'VACATION_HOME') {
      this.redirectVacationHome(id);
    } else if (type == 'BOAT') {
      this.redirectBoat(id);

    } else if (type == 'ADVENTURE') {
      this.redirectAdventure(id);
    }

  }
  redirectAdventure(id: number) {
    const adventure = {
      next: (res: any) => {
        window.location.href = '/instructor/adventure/' + res.id;
      },
      error: (err: HttpErrorResponse) => {
      }
    }
    this._service.getAdventure(id).subscribe(adventure);
  }
  redirectBoat(id: number) {
    const Boat = {
      next: (res: any) => {
        window.location.href = '/boat/page/' + res.id;
      },
      error: (err: HttpErrorResponse) => {
      }
    }
    this._service.getBoat(id).subscribe(Boat);
  }
  redirectVacationHome(id: number) {
    const vacationHome = {
      next: (res: any) => {
        window.location.href = '/home/page/' + res.id;
      },
      error: (err: HttpErrorResponse) => {
      }
    }

    this._service.getVacationHome(id).subscribe(vacationHome);
  }
}




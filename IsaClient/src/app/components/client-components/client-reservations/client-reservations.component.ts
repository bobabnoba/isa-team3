import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-client-reservations',
  templateUrl: './client-reservations.component.html',
  styleUrls: ['./client-reservations.component.css']
})
export class ClientReservationsComponent implements AfterViewInit {
  displayedColumns: string[] = ['position', 'type', 'startDate', 'endDate', 'people', 'price', 'info', 'cancel'];
  allItems!: IReservation[];
  dataSource = new MatTableDataSource<IReservation>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private _service: ReservationService,
    private _snackBar: MatSnackBar) {
    const getUpcomingReservations = {
      next: (res: any) => {
        console.log(res);
        this.dataSource.data = res;

      },
      error: (err: HttpErrorResponse) => {

      }
    }

    this._service.getUpcomingReservations().subscribe(getUpcomingReservations);
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

        this._snackBar.open('Reservation can be canceled at least 3 days before Start date. ','',
          {
            duration: 3000,
            panelClass: ['snack-bar']
          });

      }
    }

    this._service.cancelUpcomingReservation(id).subscribe(cancelReservation);

  }

  MoreInfo(type: string, id: number) {
    if (type == 'VACATION_HOME') {
      window.location.href = '/home/' + id;
    } else if (type == 'BOAT') {
      window.location.href = '/boat/' + id;
    } else if (type == 'ADVENTURE') {
      window.location.href = '/adventure/' + id;
    }

  }
}


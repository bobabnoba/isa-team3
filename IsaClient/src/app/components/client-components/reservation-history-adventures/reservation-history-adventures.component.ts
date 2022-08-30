import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { SearchFilter } from 'src/app/filters/search-filter';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { IReview } from 'src/app/interfaces/review';
import { ReservationSearchService } from 'src/app/services/reservation-search-service/reservation-search.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { ReviewService } from 'src/app/services/review-service/review.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { ReviewComponent } from '../review/review.component';

@Component({
  selector: 'app-reservation-history-adventures',
  templateUrl: './reservation-history-adventures.component.html',
  styleUrls: ['./reservation-history-adventures.component.css']
})
export class ReservationHistoryAdventuresComponent implements AfterViewInit {

  displayedColumns: string[] = ['position', 'startDate', 'endDate', 'people', 'price', 'info'];
  allItems!: IReservation[];
  dataSource = new MatTableDataSource<IReservation>();
  userEmail: string = ""
  searchFilter: SearchFilter = new SearchFilter();
  clickedRow: IReservation = {} as IReservation;
  


  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private _service: ReservationService,
    _storageService: StorageService,
    private _searchService: ReservationSearchService,
    private _matDialog: MatDialog,
    private _reviewService: ReviewService,
    private _snackBar: MatSnackBar
  ) {

    this.userEmail = _storageService.getEmail();
    const getUpcomingReservations = {
      next: (res: any) => {
        console.log(res);
        this.allItems = res;
        this.dataSource.data = res;
      },
      error: (err: HttpErrorResponse) => {
      }
    }

    this._service.getPastAdventureReservations(this.userEmail).subscribe(getUpcomingReservations);
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;

  }

  MoreInfo(id: number) {
    const adventure = {
      next: (res: any) => {
        console.log(res);
        window.location.href = '/instructor/adventure/' + res.id;

      },
      error: (err: HttpErrorResponse) => {
      }
    }
    this._service.getAdventure(id).subscribe(adventure);
  }

  onChangeDemo(ob: any) {
    console.log(this.searchFilter);
    this.dataSource.data = this._searchService.filter(this.allItems, this.searchFilter.sort)!;
  }
  clickRow(row: any) {
    console.log(row);
    this.clickedRow = row

  }
  leaveReview() {

    if (!this.clickedRow.id) {
      this._snackBar.open("Please select the reservation for which you would like to write a review.", 'Close',
        { duration: 3000 })
      return;
    }

    let checkReview = {
      next: (res: Boolean) => {
        if (res == true) {
          this._snackBar.open("You have already left a review for this reservation ! ", 'Close',
            { duration: 3000 })
          return;
        }
        else {
          this.leaveReviewPopUp();
          return;
        }
      },
      error: (err: HttpErrorResponse) => {
        this._snackBar.open("There has been a problem, please try again :( .", 'Close',
          { duration: 3000 })
        return;
      }
    }

    this._reviewService.checkForReview(this.clickedRow.id).subscribe(checkReview)

  }

  leaveReviewPopUp() {
    let myData = {
      resId: this.clickedRow.id
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '570px';
    dialogConfig.height = '500px';
    dialogConfig.data = myData;
    const dialogRef = this._matDialog.open(ReviewComponent, dialogConfig);
    dialogRef.afterClosed().subscribe({
      next: (res) => {

        let review: IReview = {
          ownerRating: res.data.ownerRating,
          rentalRating: res.data.rentalRating,
          reservationId: res.data.reservationId,
          reservationType: 'ADVENTURE',
          review: res.data.review
        }

        this._reviewService.sendReview(review, this.userEmail).subscribe({
          next: (res: any) => {
            console.log(res);
            this._snackBar.open("Your review has been sent to Admin for approval. ", 'Close',
              { duration: 3000 })
          },
          error: (err: HttpErrorResponse) => {
            this._snackBar.open("There has been a problem, please try again :( .", 'Close',
              { duration: 3000 })
            return;
          }
        }
        )
      }
    });
  }



}

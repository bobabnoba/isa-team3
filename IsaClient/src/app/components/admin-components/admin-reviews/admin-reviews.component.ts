import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AdminReview } from 'src/app/interfaces/admin-review';
import { ReviewService } from 'src/app/services/review-service/review.service';

@Component({
  selector: 'app-admin-reviews',
  templateUrl: './admin-reviews.component.html',
  styleUrls: ['./admin-reviews.component.css']
})
export class AdminReviewsComponent implements OnInit {

 
  displayedColumns: string[] = ['reservationType', 'review', 'rentalRating', 'ownerRating', 'rentalName', 'ownerEmail', 'clientEmail', 'respond'];
  dataSource = new MatTableDataSource<AdminReview>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor( private _reviewService : ReviewService, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._reviewService.getAllPending().subscribe(
      res => {
        this.dataSource = new MatTableDataSource<AdminReview>(res)
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  approve(review : AdminReview) {
    this._reviewService.handleReview(review.id, true).subscribe(
      () => {
        this.updateTable(review);
        this._snackBar.open('Review approved.', '',
          {duration : 3000, panelClass: ['snack-bar']}
        );
      }
    );
    }

  reject(review : AdminReview){
    this._reviewService.handleReview(review.id, false).subscribe(
      () => {
        this.updateTable(review);
        this._snackBar.open('Review rejected.', '',
          {duration : 3000, panelClass: ['snack-bar']}
        );
      }
    );
    }

  updateTable(review : AdminReview) {
    let idx = this.dataSource.data.indexOf(review);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

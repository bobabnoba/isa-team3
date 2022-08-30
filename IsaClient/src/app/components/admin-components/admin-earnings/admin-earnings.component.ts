import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Earnings } from 'src/app/interfaces/earnings';
import { EarningsService } from 'src/app/services/earnings-service/earnings.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-admin-earnings',
  templateUrl: './admin-earnings.component.html',
  styleUrls: ['./admin-earnings.component.css']
})
export class AdminEarningsComponent implements OnInit {

  displayedColumns: string[] = ['advertiserEmail', 'transactionDate', 'amount' ];
  dataSource = new MatTableDataSource<Earnings>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _snackBar : MatSnackBar, private _storage : StorageService, 
              private _earningsService : EarningsService, private _pipe : DatePipe) { }

  ngOnInit(): void {
      this.dataSource = new MatTableDataSource<Earnings>()
      this.dataSource.paginator = this.paginator;
  }

  dateRangeChange(dateRangeStart: HTMLInputElement, dateRangeEnd: HTMLInputElement) {


    if(dateRangeStart.value != '' && dateRangeEnd.value != '') {
    this._earningsService.getEarningsForDateRange(
      this._pipe.transform(dateRangeStart.value, 'yyyy-MM-dd')!, 
      this._pipe.transform(dateRangeEnd.value, 'yyyy-MM-dd')!,).subscribe(
        res => {
          this.dataSource = new MatTableDataSource<Earnings>(res);
          this.dataSource._updateChangeSubscription();
        })
    }
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  calculateTotal(){
    return this.dataSource.data.reduce((acc, curr) => acc + curr.amount, 0);
  }

  updateTable(earnings : Earnings) {
    let idx = this.dataSource.data.indexOf(earnings);
      this.dataSource.data.splice(idx, 1);
      this.dataSource._updateChangeSubscription();
  }
}

import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { SearchFilter } from 'src/app/filters/search-filter';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { ReservationSearchService } from 'src/app/services/reservation-search-service/reservation-search.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';


@Component({
  selector: 'app-reservation-history-homes',
  templateUrl: './reservation-history-homes.component.html',
  styleUrls: ['./reservation-history-homes.component.css']
})
export class ReservationHistoryHomesComponent implements AfterViewInit {

  displayedColumns: string[] = ['position', 'startDate', 'endDate', 'people', 'price', 'info'];
  allItems!: IReservation[];
  dataSource = new MatTableDataSource<IReservation>();
  userEmail: string = ""
  searchFilter: SearchFilter = new SearchFilter();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private _service: ReservationService,
    _storageService: StorageService,
    private _searchService: ReservationSearchService
  ) {
    this.userEmail = _storageService.getEmail();
    const getUpcomingReservations = {
      next: (res: any) => {
        console.log(res);
        this.dataSource.data = res;

      },
      error: () => {

      }
    }

    this._service.getPastVacationHomeReservations(this.userEmail).subscribe(getUpcomingReservations);
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;

  }
  MoreInfo(id: number) {

    const vacationHome = {
      next: (res: any) => {
        console.log(res);
        window.location.href = '/home/page/' + res.id;

      },
      error: (err: HttpErrorResponse) => {

      }
    }

    this._service.getVacationHome(id).subscribe(vacationHome);

  }

  onChangeDemo(ob: any) {
    console.log(this.searchFilter);
    this.dataSource.data = this._searchService.filter(this.allItems, this.searchFilter.sort)!;
  }

}

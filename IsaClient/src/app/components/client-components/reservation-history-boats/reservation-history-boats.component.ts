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
  selector: 'app-reservation-history-boats',
  templateUrl: './reservation-history-boats.component.html',
  styleUrls: ['./reservation-history-boats.component.css']
})
export class ReservationHistoryBoatsComponent implements AfterViewInit {

  displayedColumns: string[] = ['position', 'startDate', 'endDate', 'people', 'price', 'info'];
  allItems!: IReservation[];
  dataSource = new MatTableDataSource<IReservation>();
  userEmail: string = ''
  searchFilter: SearchFilter = new SearchFilter();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private _service: ReservationService,
    _storageService: StorageService,
    private _searchService: ReservationSearchService) {
    this.userEmail = _storageService.getEmail()
    const getUpcomingReservations = {
      next: (res: any) => {
        console.log(res);
        this.dataSource.data = res;

      },
      error: () => {

      }
    }

    this._service.getPastBoatReservations(this.userEmail).subscribe(getUpcomingReservations);
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;

  }

  MoreInfo(id: number) {

    const Boat = {
      next: (res: any) => {
        console.log(res);
        window.location.href = '/boat/page/' + res.id;

      },
      error: (err: HttpErrorResponse) => {

      }
    }

    this._service.getBoat(id).subscribe(Boat);

  }

  onChangeDemo(ob: any) {
    console.log(this.searchFilter);
    this.dataSource.data = this._searchService.filter(this.allItems, this.searchFilter.sort)!;
  }

}

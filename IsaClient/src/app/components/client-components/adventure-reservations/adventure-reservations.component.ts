import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SearchFilter } from 'src/app/filters/search-filter';
import { IFilter } from 'src/app/interfaces/filter';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { SearchService } from 'src/app/services/search-service/search.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-adventure-reservations',
  templateUrl: './adventure-reservations.component.html',
  styleUrls: ['./adventure-reservations.component.css']
})
export class AdventureReservationsComponent implements OnInit {
  allItems!: IProfileView[];
  filteredItems!: IProfileView[];
  requestFilter: IFilter = {} as IFilter;
  startDate: Date = new Date();
  endDate: Date = new Date();
  people: number = 2;
  datesOverlap: boolean = false;
  clientEmail!: string;
  didSearch: boolean = false;
  constructor(private _service: RentalService,
    private _searchService: SearchService,
    private _snackBar: MatSnackBar,
    private _storageService: StorageService,
    private _datePipe : DatePipe) {

  }

  ngOnInit(): void {
  }


  search(filter: SearchFilter) {
    let offerFrom = new Date(filter.startDate);
    let offerTo = new Date(filter.endDate);
    let now = new Date();
    var twentyMinutesLater = new Date( now.getTime() + (20 * 60 * 1000));

    if (offerTo.getTime() < offerFrom.getTime()) {
      this._snackBar.open('End date cannot be before start date!', '',
        { duration: 2000, panelClass: ['snack-bar'] }
      );
      return;
    }

    if (offerTo.getTime() == offerFrom.getTime()) {
      this._snackBar.open('End date cannot be same as  start date!', '',
        { duration: 2000, panelClass: ['snack-bar'] }
      );
      return;
    }
    
    if (offerFrom.getTime() <= twentyMinutesLater.getTime()) {
      this._snackBar.open('Reservation must be created at lest 20 mintes from now !', '',
        { duration: 2000, panelClass: ['snack-bar'] }
      );
      return;
    }

    this.people = filter.people;
    this.startDate = filter.startDate;
    this.endDate = filter.endDate;
    this.requestFilter.email = this._storageService.getEmail();
    this.requestFilter.endDate = this._datePipe.transform(new Date(this.endDate), 'yyyy-MM-ddTHH:mm')!;
    this.requestFilter.startDate = this._datePipe.transform(new Date(this.startDate), 'yyyy-MM-ddTHH:mm')!;
    this.requestFilter.people = this.people;

    const filterItems = {
      next: (res: any) => {
        console.log(res);
        this.didSearch = true;
        this.allItems = res;
        this.filteredItems = this._searchService.filterProfiles(this.allItems, filter)!;
        this.datesOverlap = false;

      },
      error: (err: HttpErrorResponse) => {
        this.didSearch = true;
        this.datesOverlap = true;
        this.allItems = [];
        this.filteredItems = [];
      }
    }

    this._service.filterAdventures(this.requestFilter).subscribe(filterItems);
  }
}

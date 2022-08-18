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
  today = new Date();
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
    private _storageService: StorageService,) {
    this.today.setMilliseconds(0);
  }

  ngOnInit(): void {
  }


  search(filter: SearchFilter) {

    this.people = filter.people;
    this.startDate = filter.startDate;
    this.endDate = filter.endDate;
    this.startDate.setMilliseconds(0);
    this.endDate.setMilliseconds(0);

    if (this.startDate.getTime() == this.today.getTime() && this.endDate.getTime() == this.today.getTime()) {
      this._snackBar.open('Please enter date for your reservation !', '',
        {
          duration: 3000,
          panelClass: ['snack-bar']
        });

      return;
    }
    this.requestFilter.email = this._storageService.getEmail();
    this.requestFilter.endDate = this.endDate;
    this.requestFilter.startDate = this.startDate;
    this.requestFilter.people = this.people;
    console.log(filter.startDate);
    console.log(filter.endDate);



    if (
      new Date(filter.startDate) <= this.today ||
      new Date(filter.endDate) <= this.today ||
      this.startDate > this.endDate
    ) {
      this._snackBar.open('Invalid date input!', '',
        {
          duration: 3000,
          panelClass: ['snack-bar']
        });
    } else if (filter.startDate.getDay() == filter.endDate.getDay()) {
      this._snackBar.open('Must choose two different days! ', '',
        {
          duration: 3000,
          panelClass: ['snack-bar']
        });
    }
    else {
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

}

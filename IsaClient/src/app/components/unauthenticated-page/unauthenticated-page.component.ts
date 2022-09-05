import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchFilter } from 'src/app/filters/search-filter';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { SearchService } from 'src/app/services/search-service/search.service';

@Component({
  selector: 'app-unauthenticated-page',
  templateUrl: './unauthenticated-page.component.html',
  styleUrls: ['./unauthenticated-page.component.css']
})
export class UnauthenticatedPageComponent implements OnInit {
  currentItems!: IProfileView[];
  filteredItems!: IProfileView[];
  vacation = new FormControl();
  boats = new FormControl();
  adventure = new FormControl();
  nameSearch = "";
  ratingSearch = 0;
  constructor(private _service: RentalService,private searchService: SearchService) {
    this._service.getAllRentals().subscribe(
      res => {
        console.log(res);
        
        this.currentItems = res;
        this.filteredItems = res;
      }
    )
  }

  ngOnInit(): void {

  }
  search(filter: SearchFilter) {
  
    console.log(filter);  
    this.filteredItems = this.searchService.filterUnauthProfiles(this.currentItems, filter)!;
    console.log(this.filteredItems);
  }
}

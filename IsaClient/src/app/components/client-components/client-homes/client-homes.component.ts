import { Component, OnInit } from '@angular/core';

import { SearchFilter } from 'src/app/filters/search-filter';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { SearchService } from 'src/app/services/search-service/search.service';

@Component({
  selector: 'app-client-homes',
  templateUrl: './client-homes.component.html',
  styleUrls: ['./client-homes.component.css']
})
export class ClientHomesComponent implements OnInit {
  currentItems!: IProfileView[];
  filteredItems!: IProfileView[];
  constructor(private _service: RentalService,private searchService: SearchService) {
    this._service.getAllVacationHomes().subscribe(
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
    
    this.filteredItems = this.searchService.filterProfiles(this.currentItems, filter)!;
  }

}

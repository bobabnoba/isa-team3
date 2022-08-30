import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchFilter } from 'src/app/filters/search-filter';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { SearchService } from 'src/app/services/search-service/search.service';

@Component({
  selector: 'app-client-browse',
  templateUrl: './client-browse.component.html',
  styleUrls: ['./client-browse.component.css']
})
export class ClientBrowseComponent implements OnInit {
  currentItems!: IProfileView[];
  filteredItems!: IProfileView[];
  vacation = new FormControl();
  boats = new FormControl();
  adventure = new FormControl();
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

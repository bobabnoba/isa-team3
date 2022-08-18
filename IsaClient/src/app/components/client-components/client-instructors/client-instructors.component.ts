import { Component, OnInit } from '@angular/core';
import { SearchFilter } from 'src/app/filters/search-filter';
import { InstructorBrowse } from 'src/app/interfaces/instructor-browse';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { SearchService } from 'src/app/services/search-service/search.service';


@Component({
  selector: 'app-client-instructors',
  templateUrl: './client-instructors.component.html',
  styleUrls: ['./client-instructors.component.css']
})
export class ClientInstructorsComponent implements OnInit {
  currentItems!: InstructorBrowse[];
  filteredItems!: InstructorBrowse[];
  name:string=""
  instructor:string= "instructor"
  constructor(private _service: InstructorService, private searchService: SearchService) {
    this._service.getAllActiveInstructors().subscribe(
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

    this.filteredItems = this.searchService.filterInstructors(this.currentItems, filter)!;
  }

}

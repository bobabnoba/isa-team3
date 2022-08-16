import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchFilter } from 'src/app/filters/search-filter';

@Component({
  selector: 'app-client-search-card',
  templateUrl: './client-search-card.component.html',
  styleUrls: ['./client-search-card.component.css']
})
export class ClientSearchCardComponent implements OnInit {
  @Output() doSearch: EventEmitter<SearchFilter> = new EventEmitter();
  searchFilter: SearchFilter = new SearchFilter();
  
  constructor() { }
  ngOnInit(): void {
  }

  onChangeDemo(ob: any) {
    this.filterAll();
  }

  filterAll() {
    this.doSearch.emit(this.searchFilter);
  }

  formatLabel(value: number) {
    if (value >= 0) {
      return Math.round(value);
    }

    return value;
  }

}

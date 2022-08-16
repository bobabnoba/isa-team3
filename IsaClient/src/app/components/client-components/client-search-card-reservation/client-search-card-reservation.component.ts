import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchFilter } from 'src/app/filters/search-filter';

@Component({
  selector: 'app-client-search-card-reservation',
  templateUrl: './client-search-card-reservation.component.html',
  styleUrls: ['./client-search-card-reservation.component.css']
})
export class ClientSearchCardReservationComponent implements OnInit {


  @Output() doSearch: EventEmitter<SearchFilter> = new EventEmitter();
  searchFilter: SearchFilter = new SearchFilter();
  aFormGroup!: FormGroup;

  constructor(private _formBuilder: FormBuilder) { }
  ngOnInit(): void {
    this.aFormGroup = this._formBuilder.group({
      startDate: new FormControl('', [
        Validators.required,
      ]),
      endDate: new FormControl('', [
        Validators.required,
      ]),
    });
  }

  onChangeDemo(ob: any) {
    this.filterAll();
  }

  filterAll() {
    this.doSearch.emit(this.searchFilter);

  }
  addNew() {
    if (this.aFormGroup.valid) {
      this.searchFilter.startDate = this.aFormGroup.value.startDate;
      this.searchFilter.endDate = this.aFormGroup.value.endDate;
    }
    // this.aFormGroup.reset();
    this.filterAll();
  }
  formatLabel(value: number) {
    if (value >= 0) {
      return Math.round(value);
    }

    return value;
  }

}

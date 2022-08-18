import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchFilter } from 'src/app/filters/search-filter';


@Component({
  selector: 'app-client-top-bar',
  templateUrl: './client-top-bar.component.html',
  styleUrls: ['./client-top-bar.component.css']
})
export class ClientTopBarComponent implements OnInit {


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
  startSearch() {
    console.log("bla bla");

  }
  emitMe(event: any) {
    console.log(event);

  }
  toggleMe(){
    console.log("toggle goggle");
    
  }
}

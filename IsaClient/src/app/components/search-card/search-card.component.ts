import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchFilter } from 'src/app/filters/search-filter';

@Component({
  selector: 'app-search-card',
  templateUrl: './search-card.component.html',
  styleUrls: ['./search-card.component.css']
})
export class SearchCardComponent implements OnInit {

  @Output() doSearch: EventEmitter<SearchFilter> = new EventEmitter();
  searchFilter: SearchFilter = new SearchFilter();
  pet: boolean = false;
  drinks: boolean = false;
  wifi: boolean = false;
  medic: boolean = false;
  exclusive: boolean = false;
  nameSearch = "";
  ratingSearch = 0;
  vacation = new FormControl();
  boats = new FormControl();
  instructor = new FormControl();

  constructor() { }

  ngOnInit(): void { }

  togglePet() {
    this.pet = !this.pet;
  }

  toggleDrinks() {
    this.drinks = !this.drinks;
  }

  toggleWifi() {
    this.wifi = !this.wifi;
  }

  toggleMedic() {
    this.medic = !this.medic;
  }

  toggleExclusive() {
    this.exclusive = !this.exclusive;
  }

  search() {
    this.searchFilter.tags = [];
    if (this.pet) {
      this.searchFilter.tags.push('Pet-Friendly');
    }
    if (this.medic) {
      this.searchFilter.tags.push('Health Support');
    }
    if (this.wifi) {
      this.searchFilter.tags.push('WiFi Included');
    }
    if (this.drinks) {
      this.searchFilter.tags.push('Free Drinks');
    }
    if (this.exclusive) {
      this.searchFilter.tags.push('Exclusive');
    }
    this.doSearch.emit(this.searchFilter);
  }

  onChangeDemo(ob: any) {
    this.filterAll();
  }

  filterAll() {
    this.searchFilter.tags = [];
    this.searchFilter.type = [];
    if (this.pet) {
      this.searchFilter.tags.push('Pet-Friendly');
    }
    if (this.medic) {
      this.searchFilter.tags.push('Health Support');
    }
    if (this.wifi) {
      this.searchFilter.tags.push('WiFi Included');
    }
    if (this.drinks) {
      this.searchFilter.tags.push('Free Drinks');
    }
    if (this.exclusive) {
      this.searchFilter.tags.push('Exclusive');
    }
  
    if (this.vacation.value && this.instructor.value && this.boats.value) {
    
      this.searchFilter.type.push('home', 'instructor', 'boat')
    }
    if (this.vacation.value && this.instructor.value && !this.boats.value) {
      this.searchFilter.type.push('home', 'instructor')
    }
    if (this.vacation.value && !this.instructor.value && this.boats.value) {
      this.searchFilter.type.push('home', 'boat')
    }
    if (!this.vacation.value && this.instructor.value && this.boats.value) {
      this.searchFilter.type.push('instructor', 'boat')
    }

    if (!this.vacation.value && !this.instructor.value && !this.boats.value) {
      this.searchFilter.type.push('home', 'instructor', 'boat')
    }
    if (!this.vacation.value && !this.instructor.value && this.boats.value) {
      this.searchFilter.type.push('boat')
    }
    if (!this.vacation.value && this.instructor.value && !this.boats.value) {
      this.searchFilter.type.push('instructor')
    }
    if (this.vacation.value && !this.instructor.value && !this.boats.value) {
      this.searchFilter.type.push('home')
    }
  
    this.doSearch.emit(this.searchFilter);

  }
  formatLabel(value: number) {
    if (value >= 0) {
      return Math.round(value);
    }

    return value;
  }

}

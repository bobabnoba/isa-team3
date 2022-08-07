import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { RentalService } from 'src/app/services/rental-service/rental.service';

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
  constructor(private _service: RentalService) {
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

  onChangeDemo(ob: any) {
    this.filterAll();
  }
  
  filterAll() {

    if (this.vacation.value && this.adventure.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(
        item => item.rentalType == "HOME" || item.rentalType == "BOAT" || item.rentalType == "ADVENTURE"
          && item.rating >= this.ratingSearch

          && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }
    if (this.vacation.value && this.adventure.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.rentalType != "BOAT"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }
    if (this.vacation.value && !this.adventure.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.rentalType != "ADVENTURE"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }
    if (!this.vacation.value && this.adventure.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.rentalType != "HOME"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }

    if (!this.vacation.value && !this.adventure.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item =>
        item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1
        && item.rating >= this.ratingSearch)
    }
    if (!this.vacation.value && !this.adventure.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.rentalType == "BOAT"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }
    if (!this.vacation.value && this.adventure.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.rentalType == "ADVENTURE"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }
    if (this.vacation.value && !this.adventure.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.rentalType == "HOME"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase().trim()) > -1)
    }

  }
  formatLabel(value: number) {
    if (value >= 0) {
      return Math.round(value);
    }

    return value;
  }

}

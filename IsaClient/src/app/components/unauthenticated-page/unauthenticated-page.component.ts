import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-unauthenticated-page',
  templateUrl: './unauthenticated-page.component.html',
  styleUrls: ['./unauthenticated-page.component.css']
})
export class UnauthenticatedPageComponent implements OnInit {
  currentItems!: IProfileView[];
  filteredItems!: IProfileView[];
  constructor() { }
  vacation = new FormControl();
  boats = new FormControl();
  instructor = new FormControl();
  nameSearch = "";
  ratingSearch = 0;

  ngOnInit(): void {

    this.currentItems = [
      {
        name: "Family-Friendly and Cozy Cottage in Los Feliz",
        type: "vacation",
        address: "(203) 781-8459 203 Mansfield St New Haven, Connecticut(CT), 06511",
        description: "Knowing how to write an amazing vacation rental description is essential if you want to make your vacation rental listings stand out on sales channels like Booking.com, Airbnb and Vrbo, and to quickly get the guest’s attention among hundreds of other listings. A good description should highlight the uniqueness of your vacation rentals, project a picture that matches with guests’ travel imaginations, and as a result, secure you more bookings. ",
        rating: 2
      },
      {
        name: "Terrace 2 Bedroom Apartment with WiFi – 50% OFF",
        type: "vacation",
        address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY), 14150",
        description: "Knowing how to write an amazing vacation rental description is essential if you want to make your vacation rental listings stand out on sales channels like Booking.com, Airbnb and Vrbo, and to quickly get the guest’s attention among hundreds of other listings. A good description should highlight the uniqueness of your vacation rentals, project a picture that matches with guests’ travel imaginations, and as a result, secure you more bookings. ",
        rating: 5
      },
      {
        name: "Boat, 1 min walk from Underground, Zone 2 ",
        type: "boat",
        address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY), 14150",
        description: "Beautiful holiday house in Marbella, Spain, 180m2 with four bedrooms – two double, one twin and one single. Large and bright living room with sofa bed with access to terrace, fully equipped kitchen, two bathrooms and garage.",
        rating: 4.5
      },
      {
        name: "Instructor from Las Ramblas",
        type: "instructor",
        address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY), 14150",
        description: "Rustic hilltop cabin overlooking Lake Hodges. Queen bed & small sleeping loft. Views from front & side deck, inside cabin, & outside shower. Urban amenities just a few miles away, but this small private cabin is surrounded by open canyons. A short walk to lake with boating, fishing & miles of hiking/mountain biking trails. Property offers swimming pool, fire bowl, shaded arbor and, by arrangement, yoga & massage. SD Zoo Safari Park, Stone Brewing, & ocean beaches all within easy reach.",
        rating: 3.3
      }
    ]
    this.filteredItems = this.currentItems

  }

  onChangeDemo(ob: any) {
    console.log(this.ratingSearch);
    this.filterAll();
  }

  filterAll() {
    console.log(this.nameSearch);
    if (this.vacation.value && this.instructor.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(
        item => item.type == "vacation" || item.type == "boat" || item.type == "instructor"
          && item.rating >= this.ratingSearch

          && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }
    if (this.vacation.value && this.instructor.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.type != "boat"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }
    if (this.vacation.value && !this.instructor.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.type != "instructor"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }
    if (!this.vacation.value && this.instructor.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.type != "vacation"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }

    if (!this.vacation.value && !this.instructor.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item =>
        item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1
        && item.rating >= this.ratingSearch)
    }
    if (!this.vacation.value && !this.instructor.value && this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.type == "boat"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }
    if (!this.vacation.value && this.instructor.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.type == "instructor"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }
    if (this.vacation.value && !this.instructor.value && !this.boats.value) {
      this.filteredItems = this.currentItems.filter(item => item.type == "vacation"
        && item.rating >= this.ratingSearch
        && item.name.toLowerCase().indexOf(this.nameSearch.toLowerCase()) > -1)
    }

  }
  formatLabel(value: number) {
    if (value >= 0) {
      return Math.round(value);
    }

    return value;
  }

}

import { Component, OnInit } from '@angular/core';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-unauthenticated-page',
  templateUrl: './unauthenticated-page.component.html',
  styleUrls: ['./unauthenticated-page.component.css']
})
export class UnauthenticatedPageComponent implements OnInit {
  currentItems!:IProfileView[];
  constructor() { }

  ngOnInit(): void {
  this.currentItems = [
    {
      name: "Family-Friendly and Cozy Cottage in Los Feliz", 
      address: "(203) 781-8459 203 Mansfield St New Haven, Connecticut(CT), 06511",
      description: "Knowing how to write an amazing vacation rental description is essential if you want to make your vacation rental listings stand out on sales channels like Booking.com, Airbnb and Vrbo, and to quickly get the guest’s attention among hundreds of other listings. A good description should highlight the uniqueness of your vacation rentals, project a picture that matches with guests’ travel imaginations, and as a result, secure you more bookings. ",
      rating: 5
    },
    {
      name: "Terrace 2 Bedroom Apartment with WiFi – 50% OFF", 
      address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY), 14150",
      description: "Knowing how to write an amazing vacation rental description is essential if you want to make your vacation rental listings stand out on sales channels like Booking.com, Airbnb and Vrbo, and to quickly get the guest’s attention among hundreds of other listings. A good description should highlight the uniqueness of your vacation rentals, project a picture that matches with guests’ travel imaginations, and as a result, secure you more bookings. ",
      rating: 5
    }
  ]
    
  }

}

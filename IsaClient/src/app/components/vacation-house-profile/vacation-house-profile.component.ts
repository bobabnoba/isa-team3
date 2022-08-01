import { Component, OnInit } from '@angular/core';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-vacation-house-profile',
  templateUrl: './vacation-house-profile.component.html',
  styleUrls: ['./vacation-house-profile.component.css']
})
export class VacationHouseProfileComponent implements OnInit {
  item!: IProfileView;
  constructor() { }

  ngOnInit(): void {
    this.item = 
      {
        name: "Family-Friendly and Cozy Cottage in Los Feliz",
        type: "vacation",
        address: "(203) 781-8459 203 Mansfield St New Haven, Connecticut(CT), 06511",
        description: "Knowing how to write an amazing vacation rental description is essential if you want to make your vacation rental listings stand out on sales channels like Booking.com, Airbnb and Vrbo, and to quickly get the guest’s attention among hundreds of other listings. A good description should highlight the uniqueness of your vacation rentals, project a picture that matches with guests’ travel imaginations, and as a result, secure you more bookings. ",
        rating: 2
      }
  
    }
}

import { Component, OnInit } from '@angular/core';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-boat-profile',
  templateUrl: './boat-profile.component.html',
  styleUrls: ['./boat-profile.component.css']
})
export class BoatProfileComponent implements OnInit {
  item!: IProfileView;
  constructor() { }

  ngOnInit(): void {
    this.item =
    {
      name: "YACHT CHARTER IN BELGRADE LASER PERFORMANCE — LASER 16 (1987) ",
      rentalType: "boat",
      address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY), 14150",
      description: "Unique experience of sailing under Belgrade bridges and around its islands. "
        + "\n Two to three hours (depending on weather conditions) sailing is 150€ all included. It is also available to book sailing lessons per hour."
        + "\n Laser 16 is a perfect daysailor. It is very agile, no cabin but large cockpit with lots of space for having fun while sailing, plenty of sheets to trim the sails. The boat can plain on stronger winds while it provides comfortable relaxation on lighter breeze."
        + "\n A lot of restaurants near by, from fancy to local traditional ones if you wish to eat before or after.",
      rating: 4.5,
      link :"whatever 4 now"
    }
  }

}

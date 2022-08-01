import { Component, OnInit } from '@angular/core';
import { IFishingAdventure } from 'src/app/interfaces/fishing-adventure';
import { IProfileInstructor } from 'src/app/interfaces/instructor-profile';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.css']
})
export class InstructorProfileComponent implements OnInit {
  item!: IProfileView;
  instructor!:IProfileInstructor;
  adventures!:IFishingAdventure[];
  constructor() { }

  ngOnInit(): void {
    this.instructor = {
      name: "Jeremy John Wade ",
      experience: "10 years",
      address: " Ipswich, United Kingdom",
      rating :5,
      biography:"Jeremy John Wade (born 23 March 1956) is a British television presenter, an author of books on angling, a freshwater detective, and a biologist. He is known for his television series River Monsters, Mighty Rivers, and Dark Waters."
    }
    this.adventures = [
      {
        title: "Half Day Trip (PM)",
        time: "4 Hour Trip, starts at 4:00 PM",
        description: "Get ready for a fun afternoon of fishing on the beautiful Danube River. Bring some snacks and drinks or let your guide know what you want in advance and he can prepare some nibbles and beverages for you (for a small extra fee). Tour Belgrade by boat and spend the day catching fish like Asp, Carp, Catfish, Northern Pike, Zander, and many more. All necessary equipment and fishing licenses are included, so all you have to do is show up, ready to fish, and enjoy the day with FSB Fishing Tour!",
        rating: 2,
        price: 50,
        address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY)",

      },
      {
        title: "3 Days Trip (PM)",
        time: "3 Hour Trip, starts at 4:00 PM",
        description: "Get ready for a fun afternoon of fishing on the beautiful Danube River. Bring some snacks and drinks or let your guide know what you want in advance and he can prepare some nibbles and beverages for you (for a small extra fee). Tour Belgrade by boat and spend the day catching fish like Asp, Carp, Catfish, Northern Pike, Zander, and many more. All necessary equipment and fishing licenses are included, so all you have to do is show up, ready to fish, and enjoy the day with FSB Fishing Tour!",
        rating: 4,
        price: 700,
        address: "(716) 693-1940 146 Delaware St Tonawanda, New York(NY)",

      }
    ]

  }
}

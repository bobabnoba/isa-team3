import { Component, Input, OnInit } from '@angular/core';
import { Utility } from 'src/app/interfaces/adventure';
import { Room } from 'src/app/interfaces/room';
import { VacationHome } from 'src/app/interfaces/vacation-home';

@Component({
  selector: 'app-home-services',
  templateUrl: './home-services.component.html',
  styleUrls: ['./home-services.component.css']
})
export class HomeServicesComponent implements OnInit {

  
  @Input()
  home! : VacationHome;
  
  constructor() {
    this.home = {} as VacationHome;
    this.home.utilities = [] as Utility[];
    // this.home.rooms = [] as Room[];
   }

  ngOnInit(): void {
  }

}

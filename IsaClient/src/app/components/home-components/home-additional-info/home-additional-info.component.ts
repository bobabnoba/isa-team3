import { Component, Input, OnInit } from '@angular/core';
import { Room } from 'src/app/interfaces/room';
import { Rule } from 'src/app/interfaces/rule';
import { VacationHome } from 'src/app/interfaces/vacation-home';

@Component({
  selector: 'app-home-additional-info',
  templateUrl: './home-additional-info.component.html',
  styleUrls: ['./home-additional-info.component.css']
})
export class HomeAdditionalInfoComponent implements OnInit {

  @Input()
  home! : VacationHome;
  
  constructor() {
    this.home = {} as VacationHome;
    this.home.codeOfConduct = [] as Rule[];
    this.home.rooms = [] as Room[];
   }

  ngOnInit(): void {
  }

}

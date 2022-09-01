import { Component, Input, OnInit } from '@angular/core';
import { IOwnerInfo } from 'src/app/interfaces/owner-info';
import { VacationHome } from 'src/app/interfaces/vacation-home';

@Component({
  selector: 'app-home-owner-info',
  templateUrl: './home-owner-info.component.html',
  styleUrls: ['./home-owner-info.component.css']
})
export class HomeOwnerInfoComponent implements OnInit {

  @Input()
  home! : VacationHome;  
  
  constructor() {
    this.home = {} as VacationHome;
    this.home.vacationHomeOwner = {} as IOwnerInfo;
  
   }

  ngOnInit(): void {
    console.log(this.home);
  }

}

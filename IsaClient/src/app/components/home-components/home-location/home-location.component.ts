import { Component, Input, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { VacationHome } from 'src/app/interfaces/vacation-home';

@Component({
  selector: 'app-home-location',
  templateUrl: './home-location.component.html',
  styleUrls: ['./home-location.component.css']
})
export class HomeLocationComponent implements OnInit {

  @Input()
  home! : VacationHome;  
  
  constructor() { 
    this.home = {} as VacationHome;
    this.home.address = {} as IAddress;
  }

  ngOnInit(): void {
  }

}

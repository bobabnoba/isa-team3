import { Component, Input, OnInit } from '@angular/core';
import { VacationHome } from 'src/app/interfaces/vacation-home';

@Component({
  selector: 'app-home-info',
  templateUrl: './home-info.component.html',
  styleUrls: ['./home-info.component.css']
})
export class HomeInfoComponent implements OnInit {

  @Input()
  home! : VacationHome;
  
  constructor() {
    this.home = {} as VacationHome;
  }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';

@Component({
  selector: 'app-admin-homes',
  templateUrl: './admin-homes.component.html',
  styleUrls: ['./admin-homes.component.css']
})
export class AdminHomesComponent implements OnInit {

  searchText : string = "";
  homes! : VacationHome[] ;

  constructor(private _homeService : HomeService) { }

  ngOnInit(): void {
    this._homeService.getAllHomes().subscribe(res => {
      this.homes = res;
    })
  }

  handleMe(searchText : string){
    this.searchText = searchText;
  }

  homeDeleted(id : number){
    let index = -1;
    this.homes.forEach(element => {
      if(element.id == id){
        index = this.homes.indexOf(element);
      }
    });
    if (index !== -1){
      this.homes.splice(index, 1)
      this.homes = [
        ...this.homes
      ];
    }
  }
}

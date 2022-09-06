import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { IAvailableReservations, IVacationHouseProfile } from 'src/app/interfaces/vacation-house-profile';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-unauth-home-page',
  templateUrl: './unauth-home-page.component.html',
  styleUrls: ['./unauth-home-page.component.css']
})
export class UnauthHomePageComponent implements OnInit {
  item!: VacationHome;
  actions!: IAvailableReservations[];
  roomsNumber!: number;
  bedsNumber!: number;
  id!: string;
  baseUrl = environment.apiURL
  constructor(private homeService: HomeService, private _router: Router) {
    this.id = this._router.url.substring(18) ?? '';
    console.log(this.id);
    this.getHomeDetails();

  }
  getHomeDetails() {
    const homeObserver = {
      next: (data: VacationHome) => {
        this.item = data;
        console.log(data);
        this.roomsNumber = this.item.rooms.length
        var b = 0
        this.item.rooms.forEach(x => b = b + x.numberOfBeds)
        this.bedsNumber = b;

      },
      error: (err: HttpErrorResponse) => {
        this._router.navigate(['/404']);
      },
    }
    this.homeService.getById(this.id).subscribe(homeObserver)
  }

  ngOnInit(): void {

  }

}

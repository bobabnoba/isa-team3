import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { IAvailableReservations, IVacationHouseProfile } from 'src/app/interfaces/vacation-house-profile';
@Component({
  selector: 'app-vacation-home-page',
  templateUrl: './vacation-home-page.component.html',
  styleUrls: ['./vacation-home-page.component.css']
})
export class VacationHomePageComponent implements OnInit {

  item!: IVacationHouseProfile;
  actions!: IAvailableReservations[];
  roomsNumber!: number;
  bedsNumber!: number;
  id!: string;
  constructor(private homeService: HomeService, private _router: Router) {
    this.id = this._router.url.substring(11) ?? '';
    console.log(this.id); 
    this.getHomeDetails();

  }
  getHomeDetails() {
    const homeObserver = {
      next: (data: IVacationHouseProfile) => {
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
    this.homeService.getVacationHomeDetails(this.id).subscribe(homeObserver)
  }

  ngOnInit(): void {

  }

}

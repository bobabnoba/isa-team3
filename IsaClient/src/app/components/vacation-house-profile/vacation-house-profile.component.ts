import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IAvailableReservations, IVacationHouseProfile } from 'src/app/interfaces/vacation-house-profile';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';

@Component({
  selector: 'app-vacation-house-profile',
  templateUrl: './vacation-house-profile.component.html',
  styleUrls: ['./vacation-house-profile.component.css']
})
export class VacationHouseProfileComponent implements OnInit {
  item!: IVacationHouseProfile;
  actions!: IAvailableReservations[];
  roomsNumber!: number;
  bedsNumber!: number;
  id!: string;
  constructor(private homeService: HomeService, private _router: Router) {
    this.id = this._router.url.substring(15) ?? '';
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

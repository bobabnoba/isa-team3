import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Boat } from 'src/app/interfaces/boat';
import { IVacationHouseProfile } from 'src/app/interfaces/vacation-house-profile';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { environment } from 'src/environments/environment';
import { BoatInfoComponent } from '../../boat-components/boat-info/boat-info.component';

@Component({
  selector: 'app-unauth-boat-page',
  templateUrl: './unauth-boat-page.component.html',
  styleUrls: ['./unauth-boat-page.component.css']
})
export class UnauthBoatPageComponent implements OnInit {
  item!: Boat;
 
  id!: string;
  baseUrl = environment.apiURL
  constructor(
    private _service : BoatService, 
    private _router: Router) {
    this.id = this._router.url.substring(18) ?? '';
    console.log(this.id);
    this.getHomeDetails();

  }
  getHomeDetails() {
    const homeObserver = {
      next: (data: Boat) => {
        this.item = data;
        console.log(data);

      },
      error: (err: HttpErrorResponse) => {
        this._router.navigate(['/404']);
      },
    }
    this._service.getById(this.id).subscribe(homeObserver)
  }

  ngOnInit(): void {

  }
}

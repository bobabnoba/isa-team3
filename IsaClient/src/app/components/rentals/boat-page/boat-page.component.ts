import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Boat } from 'src/app/interfaces/boat';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-boat-page',
  templateUrl: './boat-page.component.html',
  styleUrls: ['./boat-page.component.css']
})
export class BoatPageComponent implements OnInit {
  item!: Boat;
 
  id!: string;
  baseUrl = environment.apiURL
  constructor(
    private _service : BoatService, 
    private _router: Router) {
    this.id = this._router.url.substring(11) ?? '';
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

import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { IAvailableReservations, IVacationHouseProfile } from 'src/app/interfaces/vacation-house-profile';
import { ClientService } from 'src/app/services/client-service/client.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { SubService } from 'src/app/services/sub-service/sub.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-vacation-home-page',
  templateUrl: './vacation-home-page.component.html',
  styleUrls: ['./vacation-home-page.component.css']
})
export class VacationHomePageComponent implements OnInit {

  item!: VacationHome;
  actions!: IAvailableReservations[];
  roomsNumber!: number;
  bedsNumber!: number;
  id!: string;
  type:string = 'home';
  isSubscribed: boolean = false;
  userEmail: string = "";
  baseUrl = environment.apiURL;
  filteredOffers = [] as SpecialOffer[];

  constructor(
    private homeService: HomeService,
    private _clientService: ClientService,
    private _router: Router,
    private _subService: SubService,
    _storageService: StorageService,
    private _matSnackBar: MatSnackBar
  ) {
    this.id = this._router.url.substring(11) ?? '';
    this.userEmail = _storageService.getEmail();

    this.getHomeDetails();
    this.isUserSubscribed();



  }
  isUserSubscribed() {
    const isSubscribed = {
      next: (res: any) => {
        this.isSubscribed = res;
      },
      error: (err: HttpErrorResponse) => {
        this._matSnackBar.open("We are having some problems, please try later :( .", 'Close', {
          duration: 3000
        });
      },
    }
    this._clientService.isSubscribed(this.id, 'home', this.userEmail).subscribe(isSubscribed);
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
        if (data.specialOffers) {
          this.filteredOffers = data.specialOffers.filter(offer => new Date(offer.activeFrom) <= new Date()
          && new Date(offer.activeTo) >= new Date() && offer.isUsed == false);
        }
      },
      error: (err: HttpErrorResponse) => {
        this._router.navigate(['/404']);
      },
    }
    this.homeService.getById(this.id).subscribe(homeObserver)
  }
  subscribe() {
    this._subService
      .subscribe(this.id, 'home', this.userEmail)
      .subscribe({
        next: (res: any) => {
          this.isSubscribed = true;
        },
        error: (err: HttpErrorResponse) => {
          this._matSnackBar.open("We are having some problems, please try later :( .", 'Close', {
            duration: 3000
          });
        },
      });
  }

  ngOnInit(): void {

  }

}

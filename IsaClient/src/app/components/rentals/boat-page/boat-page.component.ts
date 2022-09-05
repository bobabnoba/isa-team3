import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Boat } from 'src/app/interfaces/boat';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { ClientService } from 'src/app/services/client-service/client.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { SubService } from 'src/app/services/sub-service/sub.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-boat-page',
  templateUrl: './boat-page.component.html',
  styleUrls: ['./boat-page.component.css']
})
export class BoatPageComponent implements OnInit {
  item!: Boat;
  isSubscribed: boolean = false;
  id!: string;
  type: string = 'boat';
  baseUrl = environment.apiURL
  filteredOffers = [] as SpecialOffer[];

  constructor(
    private _service: BoatService,
    private _router: Router,
    private _subService: SubService,
    private _matSnackBar: MatSnackBar,
    private _storageService: StorageService,
    private _clientService: ClientService) {
    this.id = this._router.url.substring(11) ?? '';
    console.log(this.id);
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
    this._clientService.isSubscribed(this.id, 'boat', this._storageService.getEmail()).subscribe(isSubscribed);
  }
  getHomeDetails() {
    const homeObserver = {
      next: (data: Boat) => {
        this.item = data;
        console.log(data);
        if (data.specialOffers) {
          this.filteredOffers = data.specialOffers.filter(offer => new Date(offer.activeTo) >= new Date());
        }

      },
      error: (err: HttpErrorResponse) => {
        this._router.navigate(['/404']);
      },
    }
    this._service.getById(this.id).subscribe(homeObserver)
  }

  ngOnInit(): void {

  }

  subscribe() {
    this._subService
      .subscribe(this.id, 'boat', this._storageService.getEmail())
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
}

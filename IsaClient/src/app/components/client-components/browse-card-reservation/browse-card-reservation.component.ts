import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { LoggedClient } from 'src/app/interfaces/logged-client';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { IUtility } from 'src/app/interfaces/vacation-house-profile';
import { ClientService } from 'src/app/services/client-service/client.service';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-browse-card-reservation',
  templateUrl: './browse-card-reservation.component.html',
  styleUrls: ['./browse-card-reservation.component.css']
})
export class BrowseCardReservationComponent implements OnInit {
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() address: IAddress = {
    id: 1,
    country: '',
    city: '',
    street: '',
    zipCode: 1
  };
  @Input() imageUrl: string = '';
  @Input() price: number = 0;
  @Input() duration: number = 0;
  @Input() guests: number = 0;
  @Input() rating: number = 0;
  @Input() ownerName: string = '';
  @Input() ownerId: number = 0;
  @Input() utilities: IUtility[] = [];
  @Input() imageUrls: string[] = [];
  image: any;
  @Input() id: number = 0;
  @Input() type: string = 'entity';
  @Input() startDate: Date = new Date();
  @Input() endDate: Date = new Date();
  @Input() cancelingPercentage: number = 0;
  newReservation: IReservation = {} as IReservation;
  baseUrl = environment.apiURL
  displayDiscount = false;
  client: LoggedClient = {} as LoggedClient;
  calculatedPrice: number = 0;
  showTotalPrice: boolean = false;


  constructor(
    private _rentalService: RentalService,
    private _snackBar: MatSnackBar,
    private _storageService: StorageService,
    private _datePipe: DatePipe,
    private _clientService: ClientService) {
    this.newReservation.utilities = [];

    this._clientService.getClientInfo(this._storageService.getEmail()).subscribe(
      (data) => {
        this.client = data;
        if (this.client.rank.percentage == 0) {
          this.displayDiscount = false;

        }
        this.displayDiscount = true;
        this.calculatePrice();
      
      }
    );



  }

  ngOnInit(): void {

  }

  onChangeDemo(ob: any) {

    var checked = ob.checked;
    var utilityName = ob.source.value;
    if (checked == true) {

      var utility = this.utilities.find(x => x.name == utilityName);
      if (utility != null) {
        this.newReservation.utilities.push({ name: utility.name, price: utility.price })

      }
    }
    if (checked == false) {
      var utility = this.utilities.find(x => x.name == utilityName);
      this.newReservation.utilities = this.newReservation.utilities.filter(item => item.name !== utility?.name)


    }
    console.log(this.newReservation.utilities);
    this.calculatePrice()

  }
  calculatePrice() {

    let days = new Date(this.endDate).getTime() - new Date(this.startDate).getTime()
    let numOfDays = Math.round(days / (24 * 60 * 60 * 1000));
    if (numOfDays == 0 ) {
      numOfDays = 1;
    }
    let utilitesPrice = 0;
    for (var ut of this.newReservation.utilities) {
      utilitesPrice += ut.price

    }
    let discount = 0
    let originalPrice = (numOfDays * this.price + utilitesPrice)
    
    if (this.displayDiscount) {
      discount = originalPrice * this.client.rank.percentage / 100;
    }
    this.calculatedPrice = originalPrice - discount
    this.showTotalPrice = true;
  }

  reserve() {

    this.newReservation.endDate = this._datePipe.transform(new Date(this.endDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.startDate = this._datePipe.transform(new Date(this.startDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.guests = this.guests;
    this.newReservation.price = this.price;
    this.newReservation.cancelingPercentage = this.cancelingPercentage;


    console.log(this.newReservation);
    const makeReservation = {
      next: (res: any) => {

        this._snackBar.open('Reservation made successfully!', '',
          {
            duration: 3000,
            panelClass: ['snack-bar']
          });
        window.location.href = '/client/reservations'
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
        let message = 'Sorry :( it seems like we have a problem. Try again in few minutes!'
        if (err.status == 403) {
          message = 'You have been prevented from making reservations because you have more than 3 penalties. \n ' +
            ' Penalties are deleted at the start of each month. '
        }

        this._snackBar.open(message, 'Close',
          {
            duration: 10000,
            panelClass: ['snack-bar']
          });
      }
    }
    if (this.type == 'home') {
      this._rentalService.rentVacationHome(this.newReservation, this.id, this._storageService.getEmail()).subscribe(makeReservation);
    }
    if (this.type == 'boat') {
      this._rentalService.rentBoat(this.newReservation, this.id, this._storageService.getEmail()).subscribe(makeReservation);
    }
    if (this.type == 'adventure') {
      this._rentalService.rentAdventure(this.newReservation, this.id, this._storageService.getEmail()).subscribe(makeReservation);

    }
  }
  preview() {
    if (this.type == 'adventure') {

      window.location.href = '/instructor/page/' + this.ownerId;
    } else {
      window.location.href = '/' + this.type + '/page/' + this.id;
    }
  }


}

import { MESSAGES_CONTAINER_ID } from '@angular/cdk/a11y';
import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { IUtility } from 'src/app/interfaces/vacation-house-profile';
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
  newReservation: IReservation = {} as IReservation;
  baseUrl = environment.apiURL

  constructor(
    private _rentalService: RentalService,
    private _snackBar: MatSnackBar,
    private _storageService: StorageService,
    private _datePipe: DatePipe) {
    this.newReservation.utilities = [];

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

  }

  reserve() {

    this.newReservation.endDate = this._datePipe.transform(new Date(this.endDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.startDate = this._datePipe.transform(new Date(this.startDate), 'yyyy-MM-ddTHH:mm')!;
    this.newReservation.guests = this.guests;
    this.newReservation.price = this.price;

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

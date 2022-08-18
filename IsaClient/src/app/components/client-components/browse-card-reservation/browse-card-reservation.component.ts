import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { INewReservation } from 'src/app/interfaces/new-reservation';
import { IUtility } from 'src/app/interfaces/vacation-house-profile';
import { RentalService } from 'src/app/services/rental-service/rental.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

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
  newReservation: INewReservation = {} as INewReservation;

  constructor(
    private _rentalService: RentalService,
    private _snackBar: MatSnackBar) {
    this.newReservation.utilities = [];

  }

  ngOnInit(): void {
    console.log(this.utilities);

    if (this.imageUrls.length != 0) {
      this.image = this.imageUrls[0];
    } else {
      this.image = '../../../assets/images/beach-houses.jpg';
    }
  }
  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener(
      'load',
      () => {
        this.image = reader.result;
      },
      false
    );

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(id: number) {
    // this.imageService.getImage(id).subscribe((data) => {
    //   this.createImageFromBlob(data);
    // });
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

    this.newReservation.endDate = this.endDate;
    this.newReservation.startDate = this.startDate;
    this.newReservation.guests = this.guests;
    this.newReservation.price = this.price;
  
    console.log(this.newReservation);
    const makeReservation = {
      next: (res: any) => {

        //redirect to rental page
        this._snackBar.open('Reservation made successfully!', '',
          {
            duration: 3000,
            panelClass: ['snack-bar']
          });
        window.location.href = '/client/reservations'
      },
      error: (err: HttpErrorResponse) => {
        this._snackBar.open('Sorry :( it seems like we have a problem. Try again in few minutes!', '',
          {
            duration: 3000,
            panelClass: ['snack-bar']
          });
      }
    }
    if (this.type == 'home') {
      this._rentalService.rentVacationHome(this.newReservation, this.id).subscribe(makeReservation);
    }
    if (this.type == 'boat') {
      this._rentalService.rentBoat(this.newReservation, this.id).subscribe(makeReservation);
    }
    if (this.type == 'adventure') {
      this._rentalService.rentAdventure(this.newReservation, this.id).subscribe(makeReservation);

    }
  }
  preview() {
    if (this.type == 'adventure') {
      console.log("ishere");

      window.location.href = '/instructor/' + this.ownerId;
    } else {
      window.location.href = '/' + this.type + '/' + this.id;
    }
  }


}

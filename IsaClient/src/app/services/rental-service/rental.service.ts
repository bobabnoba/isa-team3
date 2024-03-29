import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IFilter } from 'src/app/interfaces/filter';
import { IReservation } from 'src/app/interfaces/new-reservation';

@Injectable({
  providedIn: 'root'
})
export class RentalService {

  constructor(private _http: HttpClient,
  ) { }

  rentVacationHome(newReservation: IReservation, homeId: number, email: string): Observable<any> {
    return this._http.post('http://localhost:8090/vacation/homes/rent/' + homeId + "/" + email,
      newReservation);
  }
  rentBoat(newReservation: IReservation, boatId: number, email: string): Observable<any> {
    return this._http.post('http://localhost:8090/boats/rent/' + boatId + "/" + email,
      newReservation);
  }
  rentAdventure(newReservation: IReservation, adventureId: number, email: string) {
    return this._http.post('http://localhost:8090/adventures/rent/' + adventureId + "/" + email,
      newReservation);
  }

  rentAdventureInstructor(newReservation: IReservation, adventureId: number, email: string) {
    return this._http.post('http://localhost:8090/adventures/instructor-rent/' + adventureId + "/" + email,
      newReservation);
  }


  rentAdventureSpecialOffer(newReservation: IReservation, rentalId: number, offerId: number, email: string) {
    return this._http.post('http://localhost:8090/special-offers/rent/' + 'adventure/' + rentalId + "/" + offerId
      + '/' + email,
      newReservation);
  }
  rentBoatSpecialOffer(newReservation: IReservation, rentalId: number, offerId: number, email: string) {
    return this._http.post('http://localhost:8090/special-offers/rent/' + 'boat/' + rentalId + "/" + offerId
      + '/' + email,
      newReservation);
  }
  rentHomeSpecialOffer(newReservation: IReservation, rentalId: number, offerId: number, email: string) {
    return this._http.post('http://localhost:8090/special-offers/rent/' + 'home/' + rentalId + "/" + offerId
      + '/' + email,
      newReservation);
  }
  

  getAllRentals() {
    return this._http.get<any>(
      'http://localhost:8090/rentals'
    );
  }
  getAllVacationHomes() {
    return this._http.get<any>(
      'http://localhost:8090/vacation/homes'
    );
  }
  getAllBoats() {
    return this._http.get<any>(
      'http://localhost:8090/boats'
    );
  }
  filterVacationHomes(requestFilter: IFilter): Observable<any> {
    return this._http.post('http://localhost:8090/vacation/homes/search', requestFilter);
  }
  filterBoats(requestFilter: IFilter) {
    return this._http.post('http://localhost:8090/boats/search', requestFilter);
  }
  filterAdventures(requestFilter: IFilter) {
    return this._http.post('http://localhost:8090/adventures/search', requestFilter);
  }
  ownerRentBoat(newReservation: IReservation, boatId: number, email: string): Observable<any> {
    return this._http.post('http://localhost:8090/boats/owner-rent/' + boatId + "/" + email,
      newReservation);
  }
  ownerRentHome(newReservation: IReservation, homeId: number, email: string): Observable<any> {
    return this._http.post('http://localhost:8090/vacation/homes/owner-rent/' + homeId + "/" + email,
      newReservation);
  }

}


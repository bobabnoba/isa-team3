import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IFilter } from 'src/app/interfaces/filter';
import { IReservation } from 'src/app/interfaces/new-reservation';
import { StorageService } from '../storage-service/storage.service';

@Injectable({
  providedIn: 'root'
})
export class RentalService {


  userEmail: string = ""

  constructor(private _http: HttpClient,
    private _storageService: StorageService) {
    this.userEmail = this._storageService.getEmail();
  }

  rentVacationHome(newReservation: IReservation, homeId: number): Observable<any> {
    return this._http.post('http://localhost:8090/vacation/homes/rent/' + homeId + "/" + this.userEmail,
      newReservation);
  }
  rentBoat(newReservation: IReservation, boatId: number): Observable<any> {
    return this._http.post('http://localhost:8090/boats/rent/' + boatId + "/" + this.userEmail,
      newReservation);
  }
  rentAdventure(newReservation: IReservation, adventureId: number) {
    return this._http.post('http://localhost:8090/adventures/rent/' + adventureId + "/" + this.userEmail,
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

}


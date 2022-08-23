import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IFilter } from 'src/app/interfaces/filter';
import { Observable } from 'rxjs';
import { INewReservation } from 'src/app/interfaces/new-reservation';
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

  rentVacationHome(newReservation: INewReservation, homeId: number): Observable<any> {
    return this._http.post('http://localhost:8090/vacation/homes/rent/' + homeId + "/" + this.userEmail,
      newReservation);
  }
  rentBoat(newReservation: INewReservation, boatId: number) : Observable <any>{
    return this._http.post('http://localhost:8090/boats/rent/' + boatId + "/" + this.userEmail,
    newReservation);
  }
  rentAdventure(newReservation: INewReservation, adventureId: number, userEmail: string) {
    return this._http.post('http://localhost:8090/adventures/rent/' + adventureId + "/" + userEmail,
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


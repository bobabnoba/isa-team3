import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RentalService {

  constructor(private _http: HttpClient) { }

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
  getAllActiveInstructors() {
    return this._http.get<any>(
      'http://localhost:8090/instructor/available'
    );
  }

}

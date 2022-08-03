import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

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
}

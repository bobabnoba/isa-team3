import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/interfaces/reservation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  getReservation(id : number ) : Observable<Reservation> {
    return this._http.get<Reservation>(`${this.baseURL}/reservations/${id}`);
  }
}

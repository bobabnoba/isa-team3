import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BoatReservation } from 'src/app/interfaces/boat-reservation';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VacationHomeOwnerService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  getHomeOwnerUpcomingReservations(homeOwnerEmail : string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/home-owner/reservations/upcoming?homeOwnerEmail=${homeOwnerEmail}`);
  }

  getHomeOwnerReservationsHistory(homeOwnerEmail : string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/home-owner/reservations/past?homeOwnerEmail=${homeOwnerEmail}`);
  }

  getHomeOwnerCurrentReservations(homeOwnerEmail : string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/home-owner/reservations/current?homeOwnerEmail=${homeOwnerEmail}`);
  }

  getOngoingResClient(email: string) : Observable<LoggedUser>{
    return this._http.get<LoggedUser>(`${this.baseURL}/home-owner/ongoing-reservation-client/${email}`);
  }
}

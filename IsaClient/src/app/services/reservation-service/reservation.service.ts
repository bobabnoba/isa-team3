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
  constructor(
    private _http: HttpClient) {
  }

  getReservation(id: number): Observable<Reservation> {
    return this._http.get<Reservation>(`${this.baseURL}/reservations/${id}`);
  }

  getUpcomingReservations(userEmail: string) {
    return this._http.get<any>(
      `${this.baseURL}/reservations/upcoming/` + userEmail
    );
  }

  cancelUpcomingReservation(id: any, userEmail: string): Observable<any> {
    return this._http.post<any>(
      `${this.baseURL}/reservations/cancel/` + userEmail,
      id
    );
  }

  getPastVacationHomeReservations(userEmail: string): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/past/home/` + userEmail
    );
  }

  getPastAdventureReservations(userEmail: string): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/past/adventure/` + userEmail
    );
  }

  getPastBoatReservations(userEmail: string): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/past/boat/` + userEmail
    );
  }

  getAdventure(id: number): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/adventure/` + id
    );
  }

  getBoat(id: number): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/boat/` + id
    );
  }

  getVacationHome(id: number): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/vacation/home/` + id
    );
  }

  checkIfOngoingReservation(id: number): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/reservations/check-if-ongoing/` + id
    );
  }

  getReservationChartForDateRangeObject(from : string, to : string, id : number, type : string) : Observable<any[]>{
    return this._http.get<any[]>(`${this.baseURL}/reservations/chart/${type}?from=${from}&to=${to}&id=${id}`);
  }

  getReservationChartForDateRangeYearObject(from : string, to : string, id : number, type : string) : Observable<any[]>{
    return this._http.get<any[]>(`${this.baseURL}/reservations/chart-year/${type}?from=${from}&to=${to}&id=${id}`);
  }

  getReservationChartForDateRangeOwner(from : string, to : string, email : string, type : string) : Observable<any[]>{
    return this._http.get<any[]>(`${this.baseURL}/reservations/chart/${type}?from=${from}&to=${to}&email=${email}`);
  }

  getReservationChartForDateRangeYearOwner(from : string, to : string, email : string, type : string) : Observable<any[]>{
    return this._http.get<any[]>(`${this.baseURL}/reservations/chart-year/${type}?from=${from}&to=${to}&email=${email}`);
  }

}
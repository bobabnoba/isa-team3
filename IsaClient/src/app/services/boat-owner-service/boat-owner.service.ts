import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BoatReservation } from 'src/app/interfaces/boat-reservation';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {
  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  getBoatOwner(email : string ) : Observable<any> {
    email = this.handlePlusInEmail(email);
    return this._http.get(`${this.baseURL}/boat-owner?email=${email}`);
  }
  checkOwnerAvailability(from : string, to : string, boatOwnerEmail : string) {
    boatOwnerEmail = this.handlePlusInEmail(boatOwnerEmail);
    return this._http.get(`${this.baseURL}/boat-owner/check-if-available?from=${from}&to=${to}&boatOwnerEmail=${boatOwnerEmail}`);
  }
  private handlePlusInEmail(email : string) {
    return encodeURIComponent(email);
  }

  getBoatOwnerUpcomingReservations(boatOwnerEmail : string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/boat-owner/reservations/upcoming?boatOwnerEmail=${boatOwnerEmail}`);
  }

  getBoatOwnerReservationsHistory(boatOwnerEmail : string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/boat-owner/reservations/past?boatOwnerEmail=${boatOwnerEmail}`);
  }

  getBoatOwnerCurrentReservations(boatOwnerEmail : string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/boat-owner/reservations/current?boatOwnerEmail=${boatOwnerEmail}`);
  }

  getOngoingResClient(email: string) : Observable<LoggedUser>{
    return this._http.get<LoggedUser>(`${this.baseURL}/boat-owner/ongoing-reservation-client/${email}`);
  }

  getAllReservationsWhereCaptain(email: string) : Observable<BoatReservation[]>{
    return this._http.get<BoatReservation[]>(`${this.baseURL}/boat-owner/reservations/captain/${email}`);
  }

  getAllSpecialOffersWhereCaptain(email: string) : Observable<SpecialOffer[]>{
    return this._http.get<SpecialOffer[]>(`${this.baseURL}/boat-owner/special-offers/captain/${email}`);
  }

  addAvailability(body : any ) : Observable<any> {
    return this._http.post(`${this.baseURL}/boat-owner/add-availability`, body);
  }

  checkIfReservationOverlapsAvailability(body : any ) : Observable<any> {
    return this._http.post(`${this.baseURL}/boat-owner/check-if-res-overlaps-avail`, body);
  }

  removeAvailability(body : any ) : Observable<any> {
    return this._http.post(`${this.baseURL}/boat-owner/remove-availability`, body);
  }
}

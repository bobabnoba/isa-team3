import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdventureOfferInfo } from 'src/app/interfaces/adventure-offer-info';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { AdventureReservationInfo } from 'src/app/interfaces/adventure-reservation-info';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { Reservation } from 'src/app/interfaces/reservation';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {


  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  getInstructor(email : string ) : Observable<any> {
    return this._http.get(`${this.baseURL}/instructor?email=${email}`);
  }
  getInstructorInfo(id: string) : Observable<any>  {
    return this._http.get(`${this.baseURL}/instructor/info?id=${id}`);
  }
  addAvailability(body : any ) : Observable<any> {
    return this._http.post(`${this.baseURL}/instructor/add-availability`, body);
  }

  checkAvailability(from : string, to : string, instructorEmail : string) {
    return this._http.get(`${this.baseURL}/instructor/check-if-available?from=${from}&to=${to}&instructorEmail=${instructorEmail}`);
  }

  getAllActiveInstructors() {
    return this._http.get<any>(`${this.baseURL}/instructor/available`);
  }

  getInstructorUpcomingReservations(instructorEmail : string) : Observable<AdventureReservation[]>{
    return this._http.get<AdventureReservation[]>(`${this.baseURL}/instructor/reservations/upcoming?instructorEmail=${instructorEmail}`);
  }

  getInstructorReservationsHistory(instructorEmail : string) : Observable<AdventureReservation[]>{
    return this._http.get<AdventureReservation[]>(`${this.baseURL}/instructor/reservations/past?instructorEmail=${instructorEmail}`);
  }

  getOngoingResClient(email: string) : Observable<LoggedUser>{
    return this._http.get<LoggedUser>(`${this.baseURL}/instructor/ongoing-reservation-client/${email}`);
  }

  checkForOverlappingReservation(from : string, to : string, instructorEmail : string) {
    return this._http.get(`${this.baseURL}/instructor/has-overlapping-reservation?from=${from}&to=${to}&email=${instructorEmail}`);
  }

  getAllReservations(email: string) : Observable<AdventureReservationInfo[]>{
    return this._http.get<AdventureReservationInfo[]>(`${this.baseURL}/instructor/reservations/${email}`);
  }

  getAllSpecialOffers(email: string) : Observable<AdventureOfferInfo[]>{
    return this._http.get<AdventureOfferInfo[]>(`${this.baseURL}/instructor/special-offers/${email}`);
  }

}

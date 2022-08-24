import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { Reservation } from 'src/app/interfaces/reservation';
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

  getInstructorReservationsHistory(instructorEmail : string) : Observable<Reservation[]>{
    return this._http.get<Reservation[]>(`${this.baseURL}/instructor/reservations/past?instructorEmail=${instructorEmail}`);
  }
}

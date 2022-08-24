import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/interfaces/reservation';
import { environment } from 'src/environments/environment';
import { StorageService } from '../storage-service/storage.service';



@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  baseURL = environment.apiURL;
  userEmail: string = ""
  constructor(
    private _http: HttpClient,
    private _storageService: StorageService) {
    this.userEmail = this._storageService.getEmail();
  }
  getReservation(id : number ) : Observable<Reservation> {
    return this._http.get<Reservation>(`${this.baseURL}/reservations/${id}`);
  }

  getUpcomingReservations() {
    return this._http.get<any>(
      'http://localhost:8090/users/reservations/upcoming/' + this.userEmail
    );
  }
  cancelUpcomingReservation(id: any): Observable<any> {
    return this._http.post<any>(
      'http://localhost:8090/users/cancel/reservation/' + this.userEmail,
      id
    );
  }

}
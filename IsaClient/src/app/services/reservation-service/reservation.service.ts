import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../storage-service/storage.service';


@Injectable({
  providedIn: 'root'
})
export class ReservationService {


  userEmail: string = ""
  constructor(
    private _http: HttpClient,
    private _storageService: StorageService) {
    this.userEmail = this._storageService.getEmail();
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
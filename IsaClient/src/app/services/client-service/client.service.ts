import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  baseURL = environment.apiURL;

  constructor(private _http: HttpClient) { }

  checkForOverlappingReservation(from: string, to: string, email: string) {
    return this._http.get(`${this.baseURL}/clients/check-if-available?from=${from}&to=${to}&email=${email}`);
  }

  getClientInfo(email: string): Observable<any> {
    return this._http.get(`${this.baseURL}/clients/${email}`);
  }
}

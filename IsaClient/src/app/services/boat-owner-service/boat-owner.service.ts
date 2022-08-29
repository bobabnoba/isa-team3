import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
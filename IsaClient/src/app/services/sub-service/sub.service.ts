import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SubService {
  baseURL = environment.apiURL;

  constructor(private _http: HttpClient) { }

  subscribe(entityId: string, entityType: string, userEmail: string) {
    return this._http.post(`${this.baseURL}/subscriptions/subscribe/${userEmail}/${entityType}/${entityId}`, {});
  }

  unsubscribe(entityId: string | number, entityType: string, userEmail: string) {
    return this._http.post(`${this.baseURL}/subscriptions/unsubscribe/${userEmail}/${entityType}/${entityId}`, {});
  }

  getClientVacationHomeSubscriptions(clientEmail: string) {
    return this._http.get<any>(`${this.baseURL}/subscriptions/vacation/homes/${clientEmail}`);
  }
  getClientBoatSubscriptions(clientEmail: string) {
    return this._http.get<any>(`${this.baseURL}/subscriptions/boats/${clientEmail}`);
  }
  getClientInstructorsSubscriptions(clientEmail: string) {
    return this._http.get<any>(`${this.baseURL}/subscriptions/instructors/${clientEmail}`);
  }


}

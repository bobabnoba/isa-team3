import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SpecialOfferService {

  baseURL = environment.apiURL;

  constructor(private _http: HttpClient) { }

  createSpecialOffer(specialOffer: SpecialOffer, serviceId: number) {
    return this._http.post(`${this.baseURL}/special-offers/${serviceId}`, specialOffer);
  }

  getForAdventure(adventureId: string): Observable<any> {
    return this._http.get<any>(`${this.baseURL}/special-offers/adventure/${adventureId}`);
  }
}

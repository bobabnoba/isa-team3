import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SpecialOfferService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  createSpecialOffer(specialOffer : SpecialOffer, serviceId : number){
    return this._http.post(`${this.baseURL}/special-offers/${serviceId}`, specialOffer);
  }
}

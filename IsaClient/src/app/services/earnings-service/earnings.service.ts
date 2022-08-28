import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Earnings } from 'src/app/interfaces/earnings';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EarningsService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }


  getEarningsForDateRange(from : string, to : string) : Observable<Earnings[]>{
    return this._http.get<Earnings[]>(`${this.baseURL}/earnings?from=${from}&to=${to}`);
  }
}

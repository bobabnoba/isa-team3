import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const coordinatesFromAddres = 'https://nominatim.openstreetmap.org/search?format=json&limit=3&q=' ;


@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private _http : HttpClient) { }

  getCoordinates(address : string) : Observable<any>{
    return this._http.get<any>(coordinatesFromAddres + address);
  }
}

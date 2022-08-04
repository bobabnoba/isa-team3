import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Adventure } from 'src/app/interfaces/adventure';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  constructor(private _http : HttpClient) { }

  getAdventures() : Observable<Adventure[]>{
    return this._http.get<any>('http://localhost:8090/adventures');
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Adventure, FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { Rule } from 'src/app/interfaces/rule';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  constructor(private _http : HttpClient) { }

  getAdventures() : Observable<Adventure[]>{
    return this._http.get<any>('http://localhost:8090/adventures');
  }

  addAdventure(adventure : Adventure) : Observable<Adventure>{
    return this._http.post<any>('http://localhost:8090/adventures', adventure);
  }

  getFishingEquipment() : Observable<FishingEquipment[]>{
    return this._http.get<FishingEquipment[]>('http://localhost:8090/fishing-equipment');
  }

  getUtilities() : Observable<Utility[]>{
    return this._http.get<Utility[]>('http://localhost:8090/utilities');
  }

  getCodeOfConduct() : Observable<Rule[]>{
    return this._http.get<Rule[]>('http://localhost:8090/code-of-conduct');
  }
}
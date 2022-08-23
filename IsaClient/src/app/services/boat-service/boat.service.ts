import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { Rule } from 'src/app/interfaces/rule';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BoatService {
 
  addBoat(boat : Boat) : Observable<Boat>{
    return this._http.post<any>(`${this.baseURL}/boats`, boat);
  }
  getFishingEquipment() : Observable<FishingEquipment[]>{
    return this._http.get<FishingEquipment[]>(`${this.baseURL}/fishing-equipment`);
  }
  getUtilities() : Observable<Utility[]>{
    return this._http.get<Utility[]>(`${this.baseURL}/utilities`);
  }
  getCodeOfConduct() : Observable<Rule[]>{
    return this._http.get<Rule[]>(`${this.baseURL}/code-of-conduct`);
  }
  //TODO: DODATI TIP UPDATE-A
  updateInfo(id : string, updated : any) : Observable<Boat>{
    return this._http.post<Boat>(`${this.baseURL}/boats/info-update/${id}` , updated);
  }
  //TODO: DODATI TIP UPDATE-A
  updateAdditionalInfo(id : string, updated : any) : Observable<Boat>{
    return this._http.post<Boat>(`${this.baseURL}/boats/additional-update/${id}` , updated);
  }
  //TODO: DODATI TIP UPDATE-A
  updateCodeOfConduct(id : string, updated : any) : Observable<Boat>{
    return this._http.post<Boat>(`${this.baseURL}/boats/code-of-conduct-update/${id}` , updated);
  }
  //TODO: DODATI TIP UPDATE-A
  updateAddress(id : string, updated : any) : Observable<Boat>{
    return this._http.post<Boat>(`${this.baseURL}/boats/address-update/${id}` , updated);
  }
  getAllByBoatOwner(email: string) {
    return this._http.get<Boat[]>(`${this.baseURL}/boats/by-owner/${email}`);
  }
  
  baseURL = environment.apiURL;
  constructor(private _http : HttpClient) { }
  
  deleteBoat(id: number) {
    return this._http.delete<any>(`${this.baseURL}/boats/${id}`);
  }

  getBoats() : Observable<Boat[]>{
    return this._http.get<any>(`${this.baseURL}/boats`);
  }

  getById(id : string) : Observable<Boat>{
    return this._http.get<Boat>(`${this.baseURL}/boats/${id}`);
  }

  
}

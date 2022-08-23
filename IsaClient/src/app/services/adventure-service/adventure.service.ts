import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Adventure, FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { Rule } from 'src/app/interfaces/rule';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  getAdventures() : Observable<Adventure[]>{
    return this._http.get<any>(`${this.baseURL}/adventures`);
  }

  getById(id : string) : Observable<Adventure>{
    return this._http.get<Adventure>(`${this.baseURL}/adventures/${id}`);
  }

  deleteAdventure(id : number) {
    return this._http.delete<any>(`${this.baseURL}/adventures/${id}`);
  }

  getAllByInstructor(email : string) : Observable<any[]>{
    return this._http.get<any[]>(`${this.baseURL}/adventures/by-instructor/${email}`);
  } 

  addAdventure(adventure : Adventure) : Observable<Adventure>{
    return this._http.post<any>(`${this.baseURL}/adventures`, adventure);
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

  updateInfo(id : string, updated : any) : Observable<Adventure>{
    return this._http.post<Adventure>(`${this.baseURL}/adventures/info-update/${id}` , updated);
  }

  updateAdditionalInfo(id : string, updated : any) : Observable<Adventure>{
    return this._http.post<Adventure>(`${this.baseURL}/adventures/additional-update/${id}` , updated);
  }

  updateCodeOfConduct(id : string, updated : any) : Observable<Adventure>{
    return this._http.post<Adventure>(`${this.baseURL}/adventures/code-of-conduct-update/${id}` , updated);
  }

  updateAddress(id : string, updated : any) : Observable<Adventure>{
    return this._http.post<Adventure>(`${this.baseURL}/adventures/address-update/${id}` , updated);
  }
}
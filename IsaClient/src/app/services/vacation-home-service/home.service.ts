import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utility } from 'src/app/interfaces/adventure';
import { Rule } from 'src/app/interfaces/rule';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  

  baseURL = environment.apiURL;

  constructor(private _http: HttpClient) { }

  getAllHomes() : Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/vacation/homes/all`);
  }

  getVacationHomeDetails(id: string): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/vacation/homes/` + id ,
    );
  }
  getAll(): Observable<any> {
    return this._http.get<any>(
      `${this.baseURL}/vacation/homes`);
  }
  getAllByHomeOwner(email: string) : Observable<VacationHome[]> {
    return this._http.get<VacationHome[]>(`${this.baseURL}/vacation/homes/by-owner/${email}`);
  }

  getUtilities() : Observable<Utility[]>{
    return this._http.get<Utility[]>(`${this.baseURL}/utilities`);
  }
  getCodeOfConduct() : Observable<Rule[]>{
    return this._http.get<Rule[]>(`${this.baseURL}/code-of-conduct`);
  }
  addHome(home : VacationHome) : Observable<VacationHome>{
    return this._http.post<any>(`${this.baseURL}/vacation/homes`, home);
  }
  deleteHome(id: number) {
    return this._http.delete<any>(`${this.baseURL}/vacation/homes/${id}`);
  }
  
  updateInfo(id : string, updated : any) : Observable<VacationHome>{
    return this._http.post<VacationHome>(`${this.baseURL}/vacation/homes/info-update/${id}` , updated);
  }
  updateAdditionalInfo(id : string, updated : any) : Observable<VacationHome>{
    return this._http.post<VacationHome>(`${this.baseURL}/vacation/homes/additional-update/${id}` , updated);
  }
  updateCodeOfConduct(id : string, updated : any) : Observable<VacationHome>{
    return this._http.post<VacationHome>(`${this.baseURL}/vacation/homes/code-of-conduct-update/${id}` , updated);
  }
  updateAddress(id : string, updated : any) : Observable<VacationHome>{
    return this._http.post<VacationHome>(`${this.baseURL}/vacation/homes/address-update/${id}` , updated);
  }
  getById(id : string) : Observable<VacationHome>{
    return this._http.get<VacationHome>(`${this.baseURL}/vacation/homes/profile/${id}`);
  }

 
  addAvailability(body : any ) : Observable<any> {
    return this._http.post(`${this.baseURL}/vacation/homes/add-availability`, body);
  }

  checkHomeAvailability(from : string, to : string, homeId : number) {
    return this._http.get(`${this.baseURL}/vacation/homes/check-if-available?from=${from}&to=${to}&homeId=${homeId}`);
  }
  // private handlePlusInEmail(email : string) {
  //   return encodeURIComponent(email);
  // }

  getHomeForReservation(id : number) : Observable<VacationHome>{
    return this._http.get<VacationHome>(`${this.baseURL}/vacation/homes/for-reservation/${id}`);
  }

}

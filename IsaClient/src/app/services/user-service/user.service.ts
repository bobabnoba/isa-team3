import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { UserRank } from 'src/app/interfaces/user-rank';
import { environment } from 'src/environments/environment';
import { StorageService } from '../storage-service/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseURL = environment.apiURL;
  userEmail: string = ""
  constructor(
    private _http: HttpClient,
    private _storageService: StorageService) {
    this.userEmail = this._storageService.getEmail();
  }

  saveLoyaltyProgram(retVal: UserRank[]): Observable<any> {
    return this._http.post(`${this.baseURL}/user-rank`, retVal);
  }

  getUserInfo(email: string): Observable<LoggedUser> {
    return this._http.get<LoggedUser>(`${this.baseURL}/users/${email}`);
  }

  getUserById(id: number): Observable<LoggedUser> {
    return this._http.get<LoggedUser>(`${this.baseURL}/users/by-id/${id}`);
  }

  updateUser(user: LoggedUser): Observable<LoggedUser> {
    return this._http.put<LoggedUser>(`${this.baseURL}/users/update`, user);
  }

  getLoyaltyProgram(): Observable<UserRank[]> {
    return this._http.get<UserRank[]>(`${this.baseURL}/user-rank`);
  }

  changePassword( request : any, email : string) : Observable<any> {
    return this._http.put<any>(`${this.baseURL}/users/change-password/${email}`, request);
  }

}

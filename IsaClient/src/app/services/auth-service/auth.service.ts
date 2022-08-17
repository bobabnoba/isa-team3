import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { LoggedUser } from '../../interfaces/logged-user';
import { INewUser } from '../../interfaces/new-user';
import { RegisterOwner } from '../../interfaces/register-owner';
import { StorageService } from '../storage-service/storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private http: HttpClient, private _storageService: StorageService) {

  }

  login(value: any) {
    return this.http.post('http://localhost:8090/auth/login', value).pipe(
      map((response: any) => {
        if (response && response.jwt) {
          this._storageService.storeTokenData(response.jwt);
        }
      })
    );
  }

  registerUser(newUser: INewUser): Observable<LoggedUser> {
    return this.http.post<LoggedUser>(
      'http://localhost:8090/auth/register/client',
      newUser
    );
  }

  registerOwner(newUser: RegisterOwner): Observable<LoggedUser> {

    if (newUser.registrationType === "INSTRUCTOR_ADVERTISER"){
      return this.http.post<LoggedUser>(
        'http://localhost:8090/instructor/register',
        newUser
      );
    } else if (newUser.registrationType === "VACATION_HOUSE_ADVERTISER"){
      return this.http.post<LoggedUser>(
        'http://localhost:8090/home-owner/register',
        newUser
      );
    } else { //if (newUser.registrationType === "VACATION_BOAT_ADVERTISER"){
      return this.http.post<LoggedUser>(
        'http://localhost:8090/boat-owner/register',
        newUser
      );
    } 
  }

  logout() {
    this._storageService.clear();
  }
}

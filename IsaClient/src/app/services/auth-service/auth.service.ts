import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable } from 'rxjs';
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
          console.log(response.jwt)
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
    return this.http.post<LoggedUser>(
      'http://localhost:8090/auth/register/owner',
      newUser
    );
  }

  logout() {
    this._storageService.clear();
  }
}

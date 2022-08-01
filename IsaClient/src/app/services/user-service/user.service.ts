import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoggedUser } from 'src/app/interfaces/logged-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http : HttpClient) { }

  getUserInfo(email : string) : Observable<any>{
    return this._http.get('http://localhost:8090/users/'+ email);
  }

  updateUser(user : LoggedUser) : Observable<LoggedUser>{
    return this._http.put<LoggedUser>('http://localhost:8090/users/update', user);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { RegistrationRequest } from 'src/app/interfaces/registration-request';
import { UserInfo } from 'src/app/interfaces/user-info';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  baseURL = environment.apiURL;
  
  constructor(private _http: HttpClient) { }

  getAllUnhandledRegistrationRequests(): Observable<RegistrationRequest[]> {
    return this._http.get<RegistrationRequest[]>(
      `${this.baseURL}/admin/registration-requests`
    );
  }

  addNewAdmin(admin : LoggedUser) : Observable<UserInfo> {
    return this._http.post<UserInfo>(
      `${this.baseURL}/admin`, admin
    );
  }

  checkStatus(email : string) : Observable<boolean> {
    return this._http.get<boolean>(
      `${this.baseURL}/admin/first-login/${email}`
    );
  }

  handleRegistrationRequest(response : RegistrationRequest): Observable<RegistrationRequest> {
    return this._http.post<RegistrationRequest>(
      `${this.baseURL}/admin/handle-request`, response
    );
  }


}

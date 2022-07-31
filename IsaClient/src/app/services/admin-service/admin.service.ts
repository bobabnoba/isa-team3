import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegistrationRequest } from 'src/app/interfaces/registration-request';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private _http: HttpClient) { }

  getAllUnhandledRegistrationRequests(): Observable<RegistrationRequest[]> {
    return this._http.get<RegistrationRequest[]>(
      'http://localhost:8090/admin/registration-requests'
    );
  }

  handleRegistrationRequest(response : RegistrationRequest): Observable<RegistrationRequest> {
    return this._http.post<RegistrationRequest>(
      'http://localhost:8090/admin/handle-request', response
    );
  }


}

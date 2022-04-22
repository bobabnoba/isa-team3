import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IDeleteAccountRequest } from '../interfaces/delete-account-request';

@Injectable({
  providedIn: 'root'
})
export class DeleteAccountService {

  constructor(private http: HttpClient) { }

  sendRequest(newRequest: IDeleteAccountRequest) {
    return this.http.post(
      'http://localhost:8090/users/deleteAccount',
      newRequest
    );
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';

@Injectable({
  providedIn: 'root'
})
export class DeleteAccountService {

  constructor(private _http: HttpClient) {
   }

   createDeletionRequest(request: DeleteAccoutRequest) {
      return this._http.post('http://localhost:8090/delete-account', request);
   }

   getAllUprocessedRequests() : Observable<DeleteAccoutRequest[]> {
      return this._http.get<DeleteAccoutRequest[]>('http://localhost:8090/delete-account/requests');
   }

   processRequest(request : DeleteAccoutRequest) {
      return this._http.post('http://localhost:8090/delete-account/process-request', request);
   }
   
}

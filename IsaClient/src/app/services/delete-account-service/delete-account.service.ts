import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeleteAccountService {

   baseUrl = environment.apiURL;

  constructor(private _http: HttpClient) {
   }

   createDeletionRequest(request: DeleteAccoutRequest) {
      return this._http.post(`${this.baseUrl}/delete-account`, request);
   }

   getAllUprocessedRequests() : Observable<DeleteAccoutRequest[]> {
      return this._http.get<DeleteAccoutRequest[]>(`${this.baseUrl}/delete-account/requests`);
   }

   processRequest(id : number, request : DeleteAccoutRequest) {
      return this._http.post(`${this.baseUrl}/delete-account/process-request/${id}`, request);
   }
   
}

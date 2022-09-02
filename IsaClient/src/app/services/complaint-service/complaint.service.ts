import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminComplaint } from 'src/app/interfaces/admin-complaint';
import { IComplaint } from 'src/app/interfaces/complaint';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  baseURL = environment.apiURL;
  constructor(
    private _http: HttpClient) {
  }

  checkForComplaint(reservationId: number) {
    return this._http.get<Boolean>(
      `${this.baseURL}/complaints/reservation/` + reservationId,
    );
  }

  makeComplaint(review: IComplaint, userEmail: string): Observable<any> {
    return this._http.post<any>(
      `${this.baseURL}/complaints/` + userEmail,
      review
    );
  }

  getAllPending(): Observable<AdminComplaint[]> {
    return this._http.get<AdminComplaint[]>(`${this.baseURL}/complaints/pending`);
  }

  respondToComplaint(complaintId: number, response: string): Observable<any> {
    return this._http.post<any>(
      `${this.baseURL}/complaints/admin-response/${complaintId}`,
      response
    );
  }
}

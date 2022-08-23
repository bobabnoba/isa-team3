import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Report } from 'src/app/interfaces/report';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  sendReport(reservationId : number, newReport : Report) {
    return this._http.post( `${this.baseURL}/reservation-reports/${reservationId}`, newReport);
  }
  
  getAllUprocessedReports() : Observable<Report[]> {
      return this._http.get<Report[]>( `${this.baseURL}/reservation-reports/unprocessed`);
  }

  adminReview(response : any) : Observable<any> {
    return this._http.post( `${this.baseURL}/reservation-reports/admin-review`, response);
  }


}

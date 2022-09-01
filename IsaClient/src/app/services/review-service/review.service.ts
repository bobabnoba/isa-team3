import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminReview } from 'src/app/interfaces/admin-review';
import { IReview } from 'src/app/interfaces/review';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
 
  baseURL = environment.apiURL;
  constructor(
    private _http: HttpClient) {
  }

  sendReview(review: IReview, userEmail: string): Observable<any> {
    return this._http.post<any>(
      `${this.baseURL}/reviews/` + userEmail,
      review
    );
  }
  checkForReview(reservationId: number) {
    return this._http.get<Boolean>(
      `${this.baseURL}/reviews/reservation/` + reservationId,
    );
  }

  getAllPending() : Observable<AdminReview[]>{
    return this._http.get<AdminReview[]>(`${this.baseURL}/reviews/pending`);
  }

  handleReview(id : number, approved : boolean) : Observable<any>{
    return this._http.post<any>(`${this.baseURL}/reviews/handle-review/${id}`, approved);
  }

}

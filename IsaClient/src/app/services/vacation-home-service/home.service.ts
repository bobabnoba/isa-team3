import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private _http: HttpClient) { }

  getVacationHomeDetails(id: string): Observable<any> {
    return this._http.get<any>(
      'http://localhost:8090/vacation/homes/' + id ,
    );
  }
  getAll(): Observable<any> {
    return this._http.get<any>(
      'http://localhost:8090/vacation/homes');
  }

}

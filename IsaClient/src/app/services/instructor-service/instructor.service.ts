import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  constructor(private _http : HttpClient) { }

  getInstructor(email : string ) : Observable<any> {
    return this._http.get('http://localhost:8090/instructor?email=' + email);
  }

  addAvailability(body : any ) : Observable<any> {
    return this._http.post('http://localhost:8090/instructor/add-availability', body);
  }
 
}

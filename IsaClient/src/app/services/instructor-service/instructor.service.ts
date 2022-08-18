import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  baseURL = environment.apiURL;

  constructor(private _http : HttpClient) { }

  getInstructor(email : string ) : Observable<any> {
    return this._http.get(`${this.baseURL}/instructor?email=${email}`);
  }

  addAvailability(body : any ) : Observable<any> {
    return this._http.post(`${this.baseURL}/instructor/add-availability`, body);
  }

  checkAvailability(from : string, to : string, instructorEmail : string) {
    return this._http.get(`${this.baseURL}/instructor/check-if-available?from=${from}&to=${to}&instructorEmail=${instructorEmail}`);
  }
  getAllActiveInstructors() {
    return this._http.get<any>('http://localhost:8090/instructor/available');
  }

}

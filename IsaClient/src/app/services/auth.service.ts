import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { LoggedUser } from '../interfaces/logged-user';
import { INewUser } from '../interfaces/new-user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  

  //baseUrl: string = environment.baseHospitalUrl;
  private currentUserSubject: BehaviorSubject<LoggedUser>;
  public currentUser: Observable<LoggedUser>;
  private user: LoggedUser | undefined;
  private router: Router | undefined;
  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<LoggedUser>(JSON.parse((localStorage.getItem('currentUser'))!));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  login(value: any) {
    return this.http.post('http://localhost:8090/auth/login', value).pipe(
      map((response: any) => {
        if (response && response.jwt) {
          localStorage.setItem('token', response.jwt);
          localStorage.setItem('currentUser', JSON.stringify(response));
          this.currentUserSubject.next(response);
          window.location.href = "http://localhost:4200/home";
        }
        return this.user; 

      })
    );
  }

  registerUser(newUser: INewUser) : Observable<LoggedUser> {
    return this.http.post<LoggedUser>('http://localhost:8090/auth/register/client', newUser);
  }

}

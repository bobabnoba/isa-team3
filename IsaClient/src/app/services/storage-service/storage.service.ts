import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  storeTokenData(token : string) {
    localStorage.setItem('token', token);
    let decoded: any = jwt_decode(token)
    localStorage.setItem('role', decoded.role)
    localStorage.setItem('email', decoded.sub)
  }
  
  clear() : void {
    localStorage.clear()
  }

  getToken() : string {
    return localStorage.getItem('token') || ""
  }
  getRole() : string {
    return localStorage.getItem('role') || "UNAUTHENTICATED"
  }
  getEmail() : string {
    return localStorage.getItem('email') || "UNAUTHENTICATED"
  }
}

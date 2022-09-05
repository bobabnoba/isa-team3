import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import jwt_decode from "jwt-decode";


@Injectable({ providedIn: 'root' })
export class AuthGuardHomeOwner implements CanActivate {

    constructor(private _router: Router) { }

    canActivate() {
        const token = localStorage.getItem("token")
        if (token != null) {
            if(localStorage.getItem("role") == "ROLE_HOME_OWNER")
                return true;
        }

        this._router.navigate(['/403']);
        return false;
    }
}


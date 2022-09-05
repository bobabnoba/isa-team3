import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import jwt_decode from "jwt-decode";


@Injectable({ providedIn: 'root' })
export class AuthGuardAdmin implements CanActivate {

    constructor(private _router: Router) { }

    canActivate() {
        const token = localStorage.getItem("token")
        if (token != null) {
            let decoded: any = jwt_decode(token)
            if(decoded.role == "ROLE_ADMIN")
                return true;
        }

        this._router.navigate(['/403']);
        return false;
    }
}


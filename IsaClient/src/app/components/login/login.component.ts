import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
     private authService: AuthService,
     private _snackBar : MatSnackBar,
     private _router : Router,
     private _storageService : StorageService) { }

  ngOnInit(): void {
  }
  
  onSubmit(f: NgForm) {

    const loginObserver = {
      next: () => {
        this._snackBar.open('Welcome!', '',
          {duration : 3000,panelClass: ['snack-bar']}
        );

        let role = this._storageService.getRole();
        
        if (role === "ROLE_INSTRUCTOR") {
          this._router.navigate(['/instructor/dashboard']);
        } else if (role === "ROLE_ADMIN") {
          this._router.navigate(['/admin/dashboard']);
        } else {
          this._router.navigate(['/home']);
        }
      },
      error: (err: HttpErrorResponse) => {
        this._snackBar.open(err.error.message + "!", 'Dismiss');
      },
    };
    this.authService.login(f.value).subscribe(loginObserver);
  }

}






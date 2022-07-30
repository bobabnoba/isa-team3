import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
     private authService: AuthService,
     private _snackBar : MatSnackBar,
     private _router : Router) { }

  ngOnInit(): void {
  }
  
  onSubmit(f: NgForm) {

    const loginObserver = {
      next: (x: any) => {
        console.log(x);
        this._snackBar.open("Welcome!", "Dismiss");
      },
      error: (err: HttpErrorResponse) => {
        this._snackBar.open(err.error.message + "!", 'Dismiss');
      },
    };
    this.authService.login(f.value).subscribe(loginObserver);
  }

}






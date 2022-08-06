import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { INewUser } from 'src/app/interfaces/new-user';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  createForm!: FormGroup;
  formData!: FormData;
  errorMessage!: string;
  passMatch: boolean = false;
  newUser!: INewUser;
  myUser!: LoggedUser;



  constructor(
    private _formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private _router: Router,
    private _authService: AuthService
  ) {
    this.newUser = {} as INewUser;
  }

  ngOnInit(): void {
    this.createForm = this._formBuilder.group({
      firstName: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      lastName: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      adress: new FormControl('', [
        Validators.required,
      ]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
      ]),
      passConfirmed: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ])
    });
  }

  onSubmit(): void {

    this.createUser();
    this._authService.registerUser(this.newUser).subscribe({
      next: (res) => {
        this.myUser = res;
        this._router.navigate(['/']);
        this._snackBar.open('Your registration request has been sumbitted. Please check your email and confirm your email adress to activate your account.', '',
          {duration : 3000,panelClass: ['snack-bar']}
        );
      },
      error: (err: HttpErrorResponse) => {
        this._snackBar.open(err.error.message + "!", 'Dismiss',
          {duration : 3000,panelClass: ['snack-bar']});
      },
      complete: () => console.info('complete')
    });
  }

  onPasswordInput(): void {
    this.passMatch =
      this.createForm.value.password === this.createForm.value.passConfirmed;
  }

  createUser(): void {
    this.newUser.firstName = this.createForm.value.firstName;
    this.newUser.lastName = this.createForm.value.lastName;
    this.newUser.address = this.createForm.value.adress;
    this.newUser.phone = this.createForm.value.phone;
    this.newUser.email = this.createForm.value.email;
    this.newUser.city = this.createForm.value.city;
    this.newUser.country = this.createForm.value.country;
    this.newUser.password = this.createForm.value.password;
  }
}

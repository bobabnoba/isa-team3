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
import { IAddress } from 'src/app/interfaces/address';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { RegisterOwner } from 'src/app/interfaces/register-owner';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-owner-register',
  templateUrl: './owner-register.component.html',
  styleUrls: ['./owner-register.component.css'],
})
export class OwnerRegisterComponent implements OnInit {
  createForm!: FormGroup;
  formData!: FormData;
  errorMessage!: string;
  passMatch: boolean = false;
  newUser!: RegisterOwner;
  myUser!: LoggedUser;

  constructor(
    private _formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private _router: Router,
    private _authService: AuthService
  ) {
    this.newUser = {} as RegisterOwner;
    this.newUser.address = {} as IAddress;
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
      adress: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', [Validators.required]),
      motivation: new FormControl('', [Validators.required]),
      ownerType: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
      ]),
      passConfirmed: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ]),
    });
  }

  onSubmit(): void {
    this.createUser();
    this._authService.registerOwner(this.newUser).subscribe({
      next: (res) => {
        this.myUser = res;
        this._router.navigate(['/']);
        this._snackBar.open('Your registration request has been sumbitted. Admins will email you about account activation shortly.',
         '',
          {duration : 3000,panelClass: ['snack-bar']}
        );
      },
      error: (err: HttpErrorResponse) => {
        this._snackBar.open(err.error.message + '!', 'Dismiss',
          {duration : 3000,panelClass: ['snack-bar']});
      },
      complete: () => console.info('complete'),
    });
  }

  onPasswordInput(): void {
    this.passMatch =
      this.createForm.value.password === this.createForm.value.passConfirmed;
  }

  createUser(): void {
    this.newUser.firstName = this.createForm.value.firstName;
    this.newUser.lastName = this.createForm.value.lastName;
    this.newUser.address.street = this.createForm.value.adress;
    this.newUser.phone = this.createForm.value.phone;
    this.newUser.email = this.createForm.value.email;
    this.newUser.address.city = this.createForm.value.city;
    this.newUser.address.country = this.createForm.value.country;
    this.newUser.password = this.createForm.value.password;
    this.newUser.motivation = this.createForm.value.motivation;
    this.newUser.registrationType = this.createForm.value.ownerType;
    console.log(this.createForm.value.ownerType);
  }
}

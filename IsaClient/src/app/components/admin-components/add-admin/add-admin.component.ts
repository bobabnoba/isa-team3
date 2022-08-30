import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { LoggedUser } from 'src/app/interfaces/logged-user';
import { AdminService } from 'src/app/services/admin-service/admin.service';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {
  newAdminForm!: FormGroup;

  constructor( private _formBuilder: FormBuilder, private _adminService: AdminService,
     private _snackBar : MatSnackBar, private _dialogRef : MatDialogRef<AddAdminComponent>) { }

  ngOnInit(): void {
    this.newAdminForm = this._formBuilder.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      street: ['', Validators.required],
      city : ['', Validators.required],
      country : ['', Validators.required],
      zipCode:  ['', Validators.required],
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      phone: new FormControl('', [Validators.required])
    });
  }

  confirm(){
    if(this.newAdminForm.valid){
      this._adminService.addNewAdmin(this.createObject()).subscribe(
        (res)=> {
          this._snackBar.open("New admin successfully added.", "", 
          {duration: 2000,
            panelClass:['snack-bar']
          });
          this._dialogRef.close({data: {
            admin : res
          }});
        }
      );
    }
  }

  createObject() : LoggedUser {
    let newAddress = {} as IAddress;
    newAddress.street = this.newAdminForm.value.street;
    newAddress.city = this.newAdminForm.value.city;
    newAddress.country = this.newAdminForm.value.country;
    newAddress.zipCode = this.newAdminForm.value.zipCode;
    return {
      firstName: this.newAdminForm.value.firstName,
      lastName: this.newAdminForm.value.lastName,
      address : newAddress,
      email: this.newAdminForm.value.email,
      phone: this.newAdminForm.value.phone,
      password: this.newAdminForm.value.password
    } as LoggedUser;
  }

}

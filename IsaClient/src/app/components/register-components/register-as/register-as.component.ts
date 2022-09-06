import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { th } from 'date-fns/locale';

@Component({
  selector: 'app-register-as',
  templateUrl: './register-as.component.html',
  styleUrls: ['./register-as.component.css']
})
export class RegisterAsComponent implements OnInit {

  constructor(private _router: Router, private _dialogRef: MatDialogRef<RegisterAsComponent>,) { }

  ngOnInit(): void {
  }

  advertiser(){
    this._router.navigate(['/owner-register']);
    this._dialogRef.close();
  }

  client(){
    this._router.navigate(['/register']);
    this._dialogRef.close();
  }
}

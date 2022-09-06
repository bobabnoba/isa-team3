import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { RegisterAsComponent } from '../register-components/register-as/register-as.component';

@Component({
  selector: 'app-unauthenticated-header',
  templateUrl: './unauthenticated-header.component.html',
  styleUrls: ['./unauthenticated-header.component.css']
})
export class UnauthenticatedHeaderComponent implements OnInit {

  constructor(private _matDialog : MatDialog) { }

  ngOnInit(): void {
  }

  register(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '500px';
    this._matDialog.open(RegisterAsComponent, dialogConfig);
  }
}

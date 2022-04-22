import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DeleteAccountFormComponent } from '../delete-account-form/delete-account-form.component';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( public matDialog: MatDialog) { }

  ngOnInit(): void {
  }
  openModal(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.height = '400px';
    dialogConfig.width = '500px';
    this.matDialog.open(DeleteAccountFormComponent, dialogConfig);
  }

}

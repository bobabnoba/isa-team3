import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';

@Component({
  selector: 'app-instructor-sidebar',
  templateUrl: './instructor-sidebar.component.html',
  styleUrls: ['./instructor-sidebar.component.css']
})
export class InstructorSidebarComponent implements OnInit {
  
  toggle : boolean = false;

  constructor(private _storageService : StorageService, private _router : Router) { }

  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }

  logout(){
    this._storageService.clear();
    this._router.navigate(['/login']);
  }
  
}

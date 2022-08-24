import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';

@Component({
  selector: 'app-instructor-service',
  templateUrl: './instructor-service.component.html',
  styleUrls: ['./instructor-service.component.css']
})
export class InstructorServiceComponent implements OnInit {

  adventureId! : string
  instructor:boolean = false

  constructor(private _matDialog : MatDialog,
    private _router : Router,
    private _service : StorageService) { 
      if(_service.getRole() == 'ROLE_INSTRUCTOR'){
        this.instructor = true;
      }
    }

  ngOnInit(): void {
    this.adventureId = this._router.url.substring(22);
  }

}

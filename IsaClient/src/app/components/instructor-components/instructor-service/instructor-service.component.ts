import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';

@Component({
  selector: 'app-instructor-service',
  templateUrl: './instructor-service.component.html',
  styleUrls: ['./instructor-service.component.css']
})
export class InstructorServiceComponent implements OnInit {

  adventureId! : string

  constructor(private _matDialog : MatDialog, private _router : Router) { }

  ngOnInit(): void {
    this.adventureId = this._router.url.substring(22);
  }

}

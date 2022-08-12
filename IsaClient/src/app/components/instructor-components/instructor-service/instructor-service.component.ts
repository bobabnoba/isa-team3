import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';

@Component({
  selector: 'app-instructor-service',
  templateUrl: './instructor-service.component.html',
  styleUrls: ['./instructor-service.component.css']
})
export class InstructorServiceComponent implements OnInit {

  constructor(private _matDialog : MatDialog) { }

  ngOnInit(): void {
  }

  openAddAdvModal(){

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    const dialogRef = this._matDialog.open(AddAdventureComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
        console.log(res);
      })
  }

}

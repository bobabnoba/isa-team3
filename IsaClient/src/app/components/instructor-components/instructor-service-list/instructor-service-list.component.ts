import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Adventure } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';

@Component({
  selector: 'app-instructor-service-list',
  templateUrl: './instructor-service-list.component.html',
  styleUrls: ['./instructor-service-list.component.css']
})
export class InstructorServiceListComponent implements OnInit {

  adventures : Adventure[] = []

  constructor(private _adventureService: AdventureService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._adventureService.getAllByInstructor(this._storageService.getEmail()).subscribe(
      data => {
        this.adventures = data;
      }
    )
  }

  addNew(){
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    dialogConfig.data = {
      editMode : false
    }
    const dialogRef = this._matDialog.open(AddAdventureComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
          this.adventures.push(res.data.adventure) 
      })
  }

  adventureEdited(adventure : Adventure){
    console.log('dosao u roditelja emit metod, donio ovo kao avanturu')
    console.log(adventure)
    
    let index = -1;
    this.adventures.forEach(element => {
      if(element.id == adventure.id){
        index = this.adventures.indexOf(element);
      }
    });
    
    console.log('nasao indeks ' + index)
          if (index !== -1){
            this.adventures[index] = adventure
          }
  }

}

import { Component, EventEmitter, Input, OnInit, Output, ViewContainerRef } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Adventure } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { environment } from 'src/environments/environment';
import { AddAdventureComponent } from '../add-adventure/add-adventure.component';

@Component({
  selector: 'app-adventure-preview',
  templateUrl: './adventure-preview.component.html',
  styleUrls: ['./adventure-preview.component.css']
})
export class AdventurePreviewComponent implements OnInit {

  @Input()
  adventure : Adventure = {} as Adventure; 
  
  @Output() adventureEdited = new EventEmitter<Adventure>();
    
  @Output() adventureDeleted = new EventEmitter<number>();

  baseUrl = environment.apiURL

  constructor(private _router : Router, private _matDialog : MatDialog,
              private _adventureService : AdventureService) { }

  ngOnInit(): void {
  }

  openDetailed(){
    this._router.navigate(['/instructor/adventure/' + this.adventure.id]);
  }

  edit(){
    
    let myData = {
      editMode : true,
      adventureId  : this.adventure.id
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    dialogConfig.data = myData;
    const dialogRef = this._matDialog.open(AddAdventureComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
        if(res.data.editMode){
        this.adventureEdited.emit(res.data.adventure);
        }
        
      })
  }

  delete(){
    this._adventureService.deleteAdventure(this.adventure.id).subscribe(() => {
      this.adventureDeleted.emit(this.adventure.id);
    });
  }
}

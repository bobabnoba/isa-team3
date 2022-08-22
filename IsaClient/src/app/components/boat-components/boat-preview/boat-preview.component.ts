import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Boat } from 'src/app/interfaces/boat';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { environment } from 'src/environments/environment';
import { AddBoatComponent } from '../add-boat/add-boat.component';

@Component({
  selector: 'app-boat-preview',
  templateUrl: './boat-preview.component.html',
  styleUrls: ['./boat-preview.component.css']
})
export class BoatPreviewComponent implements OnInit {

  @Input()
  boat : Boat = {} as Boat; 
  
  @Output() boatEdited = new EventEmitter<Boat>();
    
  @Output() boatDeleted = new EventEmitter<number>();

  baseUrl = environment.apiURL

  constructor(private _router : Router, private _matDialog : MatDialog,
              private _boatService : BoatService) { }

  ngOnInit(): void {
  }

  openDetailed(){
    this._router.navigate(['/boat-owner/boat/' + this.boat.id]);
  }

  edit(){
    
    let myData = {
      editMode : true,
      boatId  : this.boat.id
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    dialogConfig.data = myData;
    const dialogRef = this._matDialog.open(AddBoatComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
        if(res.data.editMode){
        this.boatEdited.emit(res.data.boat);
        }
        
      })
  }

  delete(){
    this._boatService.deleteBoat(this.boat.id).subscribe(() => {
      this.boatDeleted.emit(this.boat.id);
    });
  }

}

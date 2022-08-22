import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Boat } from 'src/app/interfaces/boat';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { AddBoatComponent } from '../../boat-components/add-boat/add-boat.component';

@Component({
  selector: 'app-boat-owner-boats',
  templateUrl: './boat-owner-boats.component.html',
  styleUrls: ['./boat-owner-boats.component.css']
})
export class BoatOwnerBoatsComponent implements OnInit {

  boats : Boat[] = []
  searchText : string = "";

  constructor(private _boatService: BoatService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._boatService.getAllByBoatOwner(this._storageService.getEmail()).subscribe(
      data => {
        this.boats = data;
      }
    )
  }

  handleMe(searchText : string){
    this.searchText = searchText;
  }

  addOffer() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '600px';
    dialogConfig.height = '650px';
   // this._matDialog.open(AddSpecialOfferComponent, dialogConfig);
  }

  addNew(){
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    dialogConfig.data = {
      editMode : false
    }
    const dialogRef = this._matDialog.open(AddBoatComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
          this.boats = [
            ...this.boats,
            res.data.boat,
          ];
      })
  }

  boatEdited(boat : Boat){
    let index = -1;
    this.boats.forEach(element => {
      if(element.id == boat.id){
        index = this.boats.indexOf(element);
      }
    });
          if (index !== -1){
            this.boats[index] = boat;
            this.boats = [
              ...this.boats
            ];
          }
  }

  boatDeleted(adventureId : number){
    let index = -1;
    this.boats.forEach(element => {
      if(element.id == adventureId){
        index = this.boats.indexOf(element);
      }
    });
    if (index !== -1){
      this.boats.splice(index, 1)
      this.boats = [
        ...this.boats
      ];
    }
  }

}

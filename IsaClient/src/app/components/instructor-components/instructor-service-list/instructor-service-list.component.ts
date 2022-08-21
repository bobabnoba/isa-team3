import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject } from 'rxjs';
import { Adventure } from 'src/app/interfaces/adventure';
import { INewReservation } from 'src/app/interfaces/new-reservation';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';
import { AddSpecialOfferComponent } from '../add-special-offer/add-special-offer.component';

@Component({
  selector: 'app-instructor-service-list',
  templateUrl: './instructor-service-list.component.html',
  styleUrls: ['./instructor-service-list.component.css']
})
export class InstructorServiceListComponent implements OnInit {

  adventures : Adventure[] = []
  searchText : string = "";
  reservations : INewReservation[] = []

  constructor(private _adventureService: AdventureService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._adventureService.getAllByInstructor(this._storageService.getEmail()).subscribe(
      data => {
        this.adventures = data;
        data.forEach(d => {
          d.reservations.forEach((r : any)=> {
            this.reservations.push(r);
          });
        });
        console.log(this.reservations)
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
    this._matDialog.open(AddSpecialOfferComponent, dialogConfig);
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
        console.log(res.data.adventure)
          this.adventures = [
            ...this.adventures,
            res.data.adventure,
          ];
      })
  }

  adventureEdited(adventure : Adventure){
    let index = -1;
    this.adventures.forEach(element => {
      if(element.id == adventure.id){
        index = this.adventures.indexOf(element);
      }
    });
          if (index !== -1){
            this.adventures[index] = adventure;
            this.adventures = [
              ...this.adventures
            ];
          }
  }

  adventureDeleted(adventureId : number){
    let index = -1;
    this.adventures.forEach(element => {
      if(element.id == adventureId){
        index = this.adventures.indexOf(element);
      }
    });
    if (index !== -1){
      this.adventures.splice(index, 1)
      this.adventures = [
        ...this.adventures
      ];
    }
  }

}

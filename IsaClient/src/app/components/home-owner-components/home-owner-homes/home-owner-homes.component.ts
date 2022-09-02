import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { AddHomeComponent } from '../../home-components/add-home/add-home.component';
import { AddHomeSpecialOfferComponent } from '../add-home-special-offer/add-home-special-offer.component';

@Component({
  selector: 'app-home-owner-homes',
  templateUrl: './home-owner-homes.component.html',
  styleUrls: ['./home-owner-homes.component.css']
})
export class HomeOwnerHomesComponent implements OnInit {

  homes : VacationHome[] = []
  searchText : string = "";

  constructor(private _homeService: HomeService, private _storageService : StorageService,
              private _matDialog : MatDialog, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this._homeService.getAllByHomeOwner(this._storageService.getEmail()).subscribe(
      data => {
        this.homes = data;
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
    this._matDialog.open(AddHomeSpecialOfferComponent, dialogConfig);
  }
  

  addNew(){
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    dialogConfig.data = {
      editMode : false
    }
    const dialogRef = this._matDialog.open(AddHomeComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
          this.homes = [
            ...this.homes,
            res.data.home,
          ];
      })
  }

  homeEdited(home : VacationHome){
    let index = -1;
    this.homes.forEach(element => {
      if(element.id == home.id){
        index = this.homes.indexOf(element);
      }
    });
          if (index !== -1){
            this.homes[index] = home;
            this.homes = [
              ...this.homes
            ];
          }
  }

  homeDeleted(homeId : number){
    let index = -1;
    this.homes.forEach(element => {
      if(element.id == homeId){
        index = this.homes.indexOf(element);
      }
    });
    if (index !== -1){
      this.homes.splice(index, 1)
      this.homes = [
        ...this.homes
      ];
    }
  }

}

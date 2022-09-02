import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { environment } from 'src/environments/environment';
import { AddHomeComponent } from '../add-home/add-home.component';

@Component({
  selector: 'app-home-preview',
  templateUrl: './home-preview.component.html',
  styleUrls: ['./home-preview.component.css']
})
export class HomePreviewComponent implements OnInit {

  @Input()
  home : VacationHome = {} as VacationHome; 
  
  @Output() homeEdited = new EventEmitter<VacationHome>();
    
  @Output() homeDeleted = new EventEmitter<number>();

  baseUrl = environment.apiURL

  constructor(private _router : Router, private _matDialog : MatDialog,
              private _homeService : HomeService, public storage : StorageService, private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
  }

  openDetailed(){
    this._router.navigate(['/home-owner/home/' + this.home.id]);
  }

  edit(){
    this._homeService.incomingReservationExists(this.home.id).subscribe(res => {
      if (res) {
        this._snackBar.open('This home has booked reservations. You cannot edit it.', '', {
          duration: 2000,
          verticalPosition: 'top',
          panelClass: 'snack-bar'
        });
      } else {
        let myData = {
          editMode : true,
          homeId  : this.home.id
        }
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = false;
        dialogConfig.id = 'modal-component';
        dialogConfig.width = '1100px';
        dialogConfig.data = myData;
        const dialogRef = this._matDialog.open(AddHomeComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(res =>
          {
            if(res.data.editMode){
            this.homeEdited.emit(res.data.home);
            }
            
          })
        }
      });
  }

  delete(){
    this._homeService.incomingReservationExists(this.home.id).subscribe(res => {
      if (res) {
        this._snackBar.open('This home has booked reservations. You cannot delete it.', '', {
          duration: 2000,
          verticalPosition: 'top',
          panelClass: 'snack-bar'
        });
      } else {
        this._homeService.deleteHome(this.home.id).subscribe(() => {
          this.homeDeleted.emit(this.home.id);
        });
      }
    });
    
  }

  openCalendar(){
    this._router.navigate(['/home-owner/home/calendar/' + this.home.id]);
  }


}

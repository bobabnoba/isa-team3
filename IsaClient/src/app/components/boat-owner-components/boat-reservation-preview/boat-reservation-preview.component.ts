import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Utility } from 'src/app/interfaces/adventure';
import { Reservation } from 'src/app/interfaces/reservation';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { ClientInfoComponent } from '../../instructor-components/client-info/client-info.component';

@Component({
  selector: 'app-boat-reservation-preview',
  templateUrl: './boat-reservation-preview.component.html',
  styleUrls: ['./boat-reservation-preview.component.css']
})
export class BoatReservationPreviewComponent implements OnInit {

  @Input()
  reservation: Reservation = {} as Reservation;
  startsIn! : string;
  isClient : boolean = false;
  
  constructor(private _storageService : StorageService, private _matDialog : MatDialog) {  this.reservation.utilities = [] as Utility[]; }

  ngOnInit(): void {    
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }

  showClientInfo(){
    let myData = {
     resId : this.reservation.id
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    dialogConfig.data = myData;
    this._matDialog.open(ClientInfoComponent, dialogConfig);

  }

}

import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Adventure, Utility } from 'src/app/interfaces/adventure';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { InstructorCreateReservationComponent } from '../../instructor-components/instructor-create-reservation/instructor-create-reservation.component';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  @Input() adventureId! : string
  adventure! : Adventure;
  
  constructor(private _adventureService : AdventureService, private _matDialog : MatDialog,
    private _snackBar : MatSnackBar) { 
    this.adventure = {} as Adventure;
    this.adventure.utilities = [] as Utility[];
    this.adventure.specialOffers = [] as SpecialOffer[];
  }

  ngOnInit(): void {
    this._adventureService.getById(this.adventureId).subscribe(
      res => {
        this.adventure = res;
      }
    )
  }

  bookForClient(){

    let myData = {
      adventureId : this.adventureId,
      utilities : this.adventure.utilities,
      price : this.adventure.pricePerDay,
      guests : this.adventure.maxNumberOfParticipants,
      duration : this.adventure.durationInHours 
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.data = myData;
    this._matDialog.open(InstructorCreateReservationComponent, dialogConfig);
  }
}

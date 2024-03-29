import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Adventure, Utility } from 'src/app/interfaces/adventure';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { SpecialOfferService } from 'src/app/services/special-offer-service/special-offer.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { InstructorCreateReservationComponent } from '../../instructor-components/instructor-create-reservation/instructor-create-reservation.component';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  @Input() adventureId!: string
  adventure!: Adventure;
  instructor: boolean = false
  clientSpecialOffers: SpecialOffer[] = [];
  filteredOffers! : SpecialOffer[];
  type:string = 'adventure'

  constructor(
    private _adventureService: AdventureService,
    private _matDialog: MatDialog,
    private _service: StorageService,
    private _specialOfferService: SpecialOfferService) {

    if (_service.getRole() == 'ROLE_INSTRUCTOR') {
      this.instructor = true;
    }
    this.adventure = {} as Adventure;
    this.adventure.utilities = [] as Utility[];
    this.filteredOffers = [] as SpecialOffer[];
  }

  ngOnInit(): void {
    this._adventureService.getById(this.adventureId).subscribe(
      res => {
        this.adventure = res;
        this.filteredOffers = this.adventure.specialOffers.filter(offer => new Date(offer.activeTo) >= new Date() && offer.isUsed == false);
        this.clientSpecialOffers = this.adventure.specialOffers.filter(offer => new Date(offer.activeFrom) <= new Date()
        && new Date(offer.activeTo) >= new Date() && offer.isUsed == false);
      }
    )
  }

  bookForClient() {

    let myData = {
      adventureId: this.adventureId,
      utilities: this.adventure.utilities,
      price: this.adventure.pricePerDay,
      guests: this.adventure.maxNumberOfParticipants,
      duration: this.adventure.durationInHours,
      cancelingPercentage : this.adventure.cancelingPercentage
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.data = myData;
    this._matDialog.open(InstructorCreateReservationComponent, dialogConfig);
  }
}

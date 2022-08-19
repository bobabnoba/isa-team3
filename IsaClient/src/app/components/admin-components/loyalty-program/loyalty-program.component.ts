import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import {  UserRank } from 'src/app/interfaces/user-rank';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-loyalty-program',
  templateUrl: './loyalty-program.component.html',
  styleUrls: ['./loyalty-program.component.css']
})
export class LoyaltyProgramComponent implements OnInit {

  silverClient: UserRank = {} as UserRank;
  goldClient : UserRank = {} as UserRank;
  silverAdvertiser : UserRank = {} as UserRank;
  goldAdvertiser : UserRank = {} as UserRank;
  regularAdvertiser : UserRank = {} as UserRank;
  regularClient : UserRank = {} as UserRank;
  websiteCut : number = 0;
  program : UserRank[] = [];


  constructor(private _userService : UserService, private _snackBar : MatSnackBar, private _dialogRef : MatDialogRef<LoyaltyProgramComponent>) { }

  ngOnInit(): void {
    this._userService.getLoyaltyProgram().subscribe(data => {
      
      data.forEach(el => {
        if(this.isSilverClient(el)){
          this.silverClient = el;
        }
        if(this.isGoldClient(el)){
          this.goldClient = el;
        }
        if(this.isSilverAdvertiser(el)){
          this.silverAdvertiser = el;
        }
        if(this.isGoldAdvertiser(el)){
          this.goldAdvertiser = el;
        }
        if(this.isRegularAdvertiser(el)){
          this.regularAdvertiser = el;
          this.websiteCut = 100 - this.regularAdvertiser.percentage;
        }
        if(this.isRegularClient(el)){
          this.regularClient = el;
        }
      })
    });
  }

  save() {
    if(this.websiteCut < (100 - this.silverAdvertiser.percentage) || this.websiteCut < (100 - this.goldAdvertiser.percentage)) {
      this._snackBar.open('Website cut cannot be less than ' + (100 - this.silverAdvertiser.percentage) + '%!', '', {
        duration: 2000, panelClass: ['snack-bar']
      });
      return;
    }
    this.regularAdvertiser.percentage = 100 - this.websiteCut;
    let retVal = [] as UserRank[];
    retVal.push(this.regularClient);
    retVal.push(this.silverClient);
    retVal.push(this.goldClient);
    retVal.push(this.silverAdvertiser);
    retVal.push(this.goldAdvertiser);
    retVal.push(this.regularAdvertiser);
    this._userService.saveLoyaltyProgram(retVal).subscribe(
      () => {
        this._snackBar.open('Loyalty program successfully updated!', '',        
         { duration: 3000, panelClass: ['snack-bar'] });
        this._dialogRef.close();
        });
  }

  isSilverClient(rank : UserRank) {
    return rank.name === 'SILVER_CLIENT';
  }

  isGoldClient(rank : UserRank) {
    return rank.name === 'GOLD_CLIENT';
  }

  isSilverAdvertiser(rank : UserRank) {
    return rank.name === 'SILVER_ADVERTISER';
  }

  isGoldAdvertiser(rank : UserRank) {
    return rank.name === 'GOLD_ADVERTISER';
  }

  isRegularAdvertiser(rank : UserRank) {
    return rank.name === 'REGULAR_ADVERTISER';
  }
  isRegularClient(rank : UserRank) {
    return rank.name === 'REGULAR_CLIENT';
  }
}

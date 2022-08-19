import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Rank, UserRank } from 'src/app/interfaces/user-rank';
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
  program : UserRank[] = [];


  constructor(private _userService : UserService, private _snackBar : MatSnackBar) { }

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
        }
        if(this.isRegularClient(el)){
          this.regularClient = el;
        }
      })
    });
  }

  save() {
    let retVal = [] as UserRank[];
    retVal.push(this.regularClient);
    retVal.push(this.silverClient);
    retVal.push(this.goldClient);
    retVal.push(this.silverAdvertiser);
    retVal.push(this.goldAdvertiser);
    retVal.push(this.regularAdvertiser);
    this._userService.saveLoyaltyProgram(retVal).subscribe(
      () => {
        this._snackBar.open('Successfully saved', '',        
         { duration: 3000, panelClass: ['snack-bar'] });
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

import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { IUnsub } from 'src/app/interfaces/unsub';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { SubService } from 'src/app/services/sub-service/sub.service';


@Component({
  selector: 'app-home-subs',
  templateUrl: './home-subs.component.html',
  styleUrls: ['./home-subs.component.css']
})
export class HomeSubsComponent implements OnInit {

  currentItems!: IProfileView[];
  clientEmail: string = "";

  constructor(
    private _subService: SubService,
    _storageService: StorageService,
    private _matSnackBar: MatSnackBar
  ) {
    this.clientEmail = _storageService.getEmail();
    this._subService.getClientVacationHomeSubscriptions(this.clientEmail).subscribe({
      next: (res: any) => {
        this.currentItems = res;
        console.log(res);

      },
      error: (err: HttpErrorResponse) => {
        this._matSnackBar.open("We are having some problems, please try again later :( .", 'Close', {
          duration: 3000
        });
      }
    }
    )
  }

  ngOnInit(): void {
  }

  unsubscribe(unsub: IUnsub) {
    console.log(unsub);

    this._subService
      .unsubscribe(unsub.id, unsub.type, this.clientEmail)
      .subscribe({
        next: (res: any) => {
          var sub = this.currentItems.find(x => x.id == unsub.id);
          this.currentItems = this.currentItems.filter(item => item.id !== sub?.id)

        },
        error: (err: HttpErrorResponse) => {
          this._matSnackBar.open("We are having some problems, please try later :( .", 'Close', {
            duration: 3000
          });
        },
      });
  }

}

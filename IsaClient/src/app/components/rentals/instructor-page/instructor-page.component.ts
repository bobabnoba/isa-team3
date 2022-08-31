import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Adventure } from 'src/app/interfaces/adventure';
import { IInstructorInfo } from 'src/app/interfaces/instructor-info';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { ClientService } from 'src/app/services/client-service/client.service';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { SubService } from 'src/app/services/sub-service/sub.service';

@Component({
  selector: 'app-instructor-page',
  templateUrl: './instructor-page.component.html',
  styleUrls: ['./instructor-page.component.css']
})
export class InstructorPageComponent implements OnInit {

  item!: IInstructorInfo;
  adventures: Adventure[] = []
  id!: string;
  isSubscribed: boolean = false;
  userEmail: string = "";

  constructor(
    private service: InstructorService,
    private _router: Router,
    private _adventureService: AdventureService,
    private _matSnackBar: MatSnackBar,
    private _subService: SubService,
    private _clientService: ClientService,
    private _storageService: StorageService
  ) {
    this.id = this._router.url.substring(17) ?? '';
    this.userEmail = _storageService.getEmail();

    this.getDetails();
    this.getAdventures();
    this.isUserSubscribed();
  }
  isUserSubscribed() {
    const isSubscribed = {
      next: (res: any) => {
        this.isSubscribed = res;
      },
      error: (err: HttpErrorResponse) => {
        this._matSnackBar.open("We are having some problems, please try later :( .", 'Close', {
          duration: 3000
        });
      },
    }
    this._clientService.isSubscribed(this.id, 'instructor', this.userEmail).subscribe(isSubscribed);
  }
  getAdventures() {
    this._adventureService.getAllByInstructorId(this.id).subscribe(
      data => {
        this.adventures = data;
      }

    )
  }
  getDetails() {
    const instructorObserver = {
      next: (data: any) => {
        this.item = data;
        console.log(data);

      },
      error: (err: HttpErrorResponse) => {
        this._router.navigate(['/404']);
      },
    }
    this.service.getInstructorInfo(this.id).subscribe(instructorObserver);
  }
  subscribe() {
    this._subService
      .subscribe(this.id, 'instructor', this.userEmail)
      .subscribe({
        next: (res: any) => {
          this.isSubscribed = true;
        },
        error: (err: HttpErrorResponse) => {
          this._matSnackBar.open("We are having some problems, please try later :( .", 'Close', {
            duration: 3000
          });
        },
      });
  }

  ngOnInit(): void {
  }

}

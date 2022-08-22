import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boat-owner-boat-profile',
  templateUrl: './boat-owner-boat-profile.component.html',
  styleUrls: ['./boat-owner-boat-profile.component.css']
})
export class BoatOwnerBoatProfileComponent implements OnInit {

  boatId! : string

  constructor(private _matDialog : MatDialog, private _router : Router) { }

  ngOnInit(): void {
    this.boatId = this._router.url.substring(17);
  }

}

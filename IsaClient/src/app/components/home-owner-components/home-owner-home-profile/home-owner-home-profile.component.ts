import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-owner-home-profile',
  templateUrl: './home-owner-home-profile.component.html',
  styleUrls: ['./home-owner-home-profile.component.css']
})
export class HomeOwnerHomeProfileComponent implements OnInit {

  homeId! : string

  constructor(private _matDialog : MatDialog, private _router : Router) { }

  ngOnInit(): void {
    this.homeId = this._router.url.substring(17);
  }

}

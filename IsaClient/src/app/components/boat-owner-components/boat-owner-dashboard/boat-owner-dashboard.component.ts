import { Component, OnInit } from '@angular/core';
import { BoatOwnerService } from 'src/app/services/boat-owner-service/boat-owner.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-boat-owner-dashboard',
  templateUrl: './boat-owner-dashboard.component.html',
  styleUrls: ['./boat-owner-dashboard.component.css']
})
export class BoatOwnerDashboardComponent implements OnInit {

  email : string = '';
  constructor(private _boatOwnerService : BoatOwnerService, private _storageService: StorageService) {
    this.email = this._storageService.getEmail();
   }

  ngOnInit(): void {
  
  }

}

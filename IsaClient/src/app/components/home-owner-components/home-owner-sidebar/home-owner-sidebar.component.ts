import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-home-owner-sidebar',
  templateUrl: './home-owner-sidebar.component.html',
  styleUrls: ['./home-owner-sidebar.component.css']
})
export class HomeOwnerSidebarComponent implements OnInit {

  
  toggle : boolean = false;

  constructor(private _storageService : StorageService, private _router : Router) { }

  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }

  logout(){
    this._storageService.clear();
    this._router.navigate(['/login']);
  }


}

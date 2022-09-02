import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Utility } from 'src/app/interfaces/adventure';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';

@Component({
  selector: 'app-home-owner-topbar',
  templateUrl: './home-owner-topbar.component.html',
  styleUrls: ['./home-owner-topbar.component.css']
})
export class HomeOwnerTopbarComponent implements OnInit {

  @Output() searchInput : EventEmitter<string> = new EventEmitter();
  @Input() home : string = ""
  toggle : boolean = false;

  serviceSearch : boolean = false;

  existingServices! : Utility[]

  services : Utility[] = [] as Utility[]

  servi = new FormControl()

  constructor(private _router : Router, private _homeService : HomeService) { 
    if(this._router.url === "/home-owner/homes"){
      this.serviceSearch = true;
    }
  }

  ngOnInit(): void {
    
    
    this._homeService.getUtilities().subscribe(
      data => {
        this.existingServices = data;
      }
    )
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }
  
  emitMe( searchText : any){
    this.searchInput.emit(searchText.target.value);
  }

  startSearch(){
    let s = ''

    if (this.services.length > 0) {
      this.services.forEach( srv => {
        s += srv.name + ' '
      })
    }
    this.searchInput.emit(s);
  }


  servicesPicked(){
    let s = ''
    if (this.services.length > 0) {
      this.services.forEach( srv => {
        s += srv.name + ' '
      })
    }

    
    this.searchInput.emit(s);
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }

}

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { BoatService } from 'src/app/services/boat-service/boat.service';

@Component({
  selector: 'app-boat-owner-topbar',
  templateUrl: './boat-owner-topbar.component.html',
  styleUrls: ['./boat-owner-topbar.component.css']
})
export class BoatOwnerTopbarComponent implements OnInit {

  @Output() searchInput : EventEmitter<string> = new EventEmitter();
  @Input() boat : string = ""
  toggle : boolean = false;

  serviceSearch : boolean = false;

  existingEquipment! : FishingEquipment[]
  existingServices! : Utility[]

  equipment : FishingEquipment[] = [] as FishingEquipment[]
  services : Utility[] = [] as Utility[]

  equi = new FormControl()
  servi = new FormControl()

  constructor(private _router : Router, private _boatService : BoatService) { 
    if(this._router.url === "/boat-owner/boats"){
      this.serviceSearch = true;
    }
  }

  ngOnInit(): void {
    this._boatService.getFishingEquipment().subscribe(
      data => {
        this.existingEquipment = data;
      }
    );
    
    this._boatService.getUtilities().subscribe(
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
    if(this.equipment.length > 0){
      this.equipment.forEach( e => {
        s += e.name + ' '
      })
    }

    if (this.services.length > 0) {
      this.services.forEach( srv => {
        s += srv.name + ' '
      })
    }
    this.searchInput.emit(s);
  }

  equipmentPicked(){
    let s = ''
    if(this.equipment.length > 0){
      this.equipment.forEach( e => {
        s += e.name + ' '
      })
    }

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
    if(this.equipment.length > 0){
      this.equipment.forEach( e => {
        s += e.name + ' '
      })
    }

    
    this.searchInput.emit(s);
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }

}

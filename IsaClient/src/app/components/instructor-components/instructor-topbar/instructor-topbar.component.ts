import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';

@Component({
  selector: 'app-instructor-topbar',
  templateUrl: './instructor-topbar.component.html',
  styleUrls: ['./instructor-topbar.component.css']
})
export class InstructorTopbarComponent implements OnInit {

  @Output() searchInput : EventEmitter<string> = new EventEmitter();
  @Input() instructor : string = ""
  toggle : boolean = false;

  serviceSearch : boolean = false;

  existingEquipment! : FishingEquipment[]
  existingServices! : Utility[]

  equipment : FishingEquipment[] = [] as FishingEquipment[]
  services : Utility[] = [] as Utility[]

  equi = new FormControl()
  servi = new FormControl()

  constructor(private _router : Router, private _adventureService : AdventureService) { 
    if(this._router.url === "/instructor/services"){
      this.serviceSearch = true;
    }
  }

  ngOnInit(): void {
    this._adventureService.getFishingEquipment().subscribe(
      data => {
        this.existingEquipment = data;
      }
    );
    
    this._adventureService.getUtilities().subscribe(
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

  toggleMe(){
    this.toggle = !this.toggle;
  }
}

import { Component, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';

@Component({
  selector: 'app-admin-adventures',
  templateUrl: './admin-adventures.component.html',
  styleUrls: ['./admin-adventures.component.css']
})
export class AdminAdventuresComponent implements OnInit {

  adventures : Adventure[] = []
  searchText : string = "";
  
  constructor(private _adventureService: AdventureService) { }

  ngOnInit(): void {
    this._adventureService.getAdventures().subscribe(
      res => {
        this.adventures = res;
      }
    );
  }

  handleMe(searchText : string){
    this.searchText = searchText;
  } 

  adventureDeleted(adventureId : number){
    let index = -1;
    this.adventures.forEach(element => {
      if(element.id == adventureId){
        index = this.adventures.indexOf(element);
      }
    });
    if (index !== -1){
      this.adventures.splice(index, 1)
      this.adventures = [
        ...this.adventures
      ];
    }
  }
}

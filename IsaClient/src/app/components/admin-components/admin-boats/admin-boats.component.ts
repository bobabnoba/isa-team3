import { Component, OnInit } from '@angular/core';
import { Boat } from 'src/app/interfaces/boat';
import { BoatService } from 'src/app/services/boat-service/boat.service';

@Component({
  selector: 'app-admin-boats',
  templateUrl: './admin-boats.component.html',
  styleUrls: ['./admin-boats.component.css']
})
export class AdminBoatsComponent implements OnInit {

  boats : Boat[] = []
  searchText : string = "";
  
  constructor(private _boatService: BoatService) { }

  ngOnInit(): void {
    this._boatService.getBoats().subscribe(res => {
      this.boats = res;
    })
  }

  handleMe(searchText : string){
    this.searchText = searchText;
  }

  boatDeleted(adventureId : number){
    let index = -1;
    this.boats.forEach(element => {
      if(element.id == adventureId){
        index = this.boats.indexOf(element);
      }
    });
    if (index !== -1){
      this.boats.splice(index, 1)
      this.boats = [
        ...this.boats
      ];
    }
  }

}

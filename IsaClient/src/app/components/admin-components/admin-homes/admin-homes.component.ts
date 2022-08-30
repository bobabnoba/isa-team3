import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-homes',
  templateUrl: './admin-homes.component.html',
  styleUrls: ['./admin-homes.component.css']
})
export class AdminHomesComponent implements OnInit {

  searchText : string = "";

  constructor() { }

  ngOnInit(): void {
  }

  handleMe(searchText : string){
    this.searchText = searchText;
  }
}

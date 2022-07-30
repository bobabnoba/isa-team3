import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrls: ['./admin-sidebar.component.css']
})
export class AdminSidebarComponent implements OnInit {

  toggle : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }
}

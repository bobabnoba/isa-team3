import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-instructor-sidebar',
  templateUrl: './instructor-sidebar.component.html',
  styleUrls: ['./instructor-sidebar.component.css']
})
export class InstructorSidebarComponent implements OnInit {
  
  toggle : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }
}

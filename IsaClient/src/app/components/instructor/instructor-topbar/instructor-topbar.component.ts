import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-instructor-topbar',
  templateUrl: './instructor-topbar.component.html',
  styleUrls: ['./instructor-topbar.component.css']
})
export class InstructorTopbarComponent implements OnInit {

  constructor() { }

  toggle : boolean = false;

  
  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }
}

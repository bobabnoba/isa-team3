import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';

@Component({
  selector: 'app-adventure-instructor',
  templateUrl: './adventure-instructor.component.html',
  styleUrls: ['./adventure-instructor.component.css']
})
export class AdventureInstructorComponent implements OnInit {

  @Input()
  adventure! : Adventure;  

  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.css']
})
export class ProfileViewComponent implements OnInit {
  @Input() 
  items!:IProfileView[]
  constructor() { }

  ngOnInit(): void {
  }

}

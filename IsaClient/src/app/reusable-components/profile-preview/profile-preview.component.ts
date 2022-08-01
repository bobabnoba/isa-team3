import { Component, Input, OnInit } from '@angular/core';
import { IProfileView } from 'src/app/interfaces/profile-view';

@Component({
  selector: 'app-profile-preview',
  templateUrl: './profile-preview.component.html',
  styleUrls: ['./profile-preview.component.css']
})
export class ProfilePreviewComponent implements OnInit {
  @Input() 
  items!:IProfileView[]
  constructor() { }

  ngOnInit(): void {
  }

}


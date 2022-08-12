import { Component, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  adventure! : Adventure;
  
  constructor(private _adventureService : AdventureService) { }

  ngOnInit(): void {
    this._adventureService.getAdventures().subscribe(
      res => this.adventure = res[0]
    )
  }

}

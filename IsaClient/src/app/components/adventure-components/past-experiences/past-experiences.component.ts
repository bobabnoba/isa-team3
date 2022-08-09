import { Component, Input, OnInit } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-past-experiences',
  templateUrl: './past-experiences.component.html',
  styleUrls: ['./past-experiences.component.css']
})
export class PastExperiencesComponent implements OnInit {

  @Input()
  adventure! : Adventure;
  img : string = ""
  baseUrl = environment.apiURL
  constructor() { }

  ngOnInit(): void {
    console.log(this.adventure)
    this.img = environment.apiURL + "/" + this.adventure.images[0]
    }

}

import { Component, Input, OnInit } from '@angular/core';
import { IFishingAdventure } from 'src/app/interfaces/fishing-adventure';


@Component({
  selector: 'app-adventure-preview',
  templateUrl: './adventure-preview.component.html',
  styleUrls: ['./adventure-preview.component.css']
})
export class AdventurePreviewComponent implements OnInit {

  @Input() 
  items!:IFishingAdventure[]
  constructor() { }

  ngOnInit(): void {
  }

}
// export interface IFishingAdventure{
//   title: string,
//   time: string,
//   description: string
//   rating: number,
//   price: number,
//   address: string,
// }

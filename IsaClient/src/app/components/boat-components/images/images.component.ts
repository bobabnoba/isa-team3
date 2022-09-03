import { Component, Input, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

  @Input()
  images! : string[];
  baseUrl = environment.apiURL

  constructor() { }

  ngOnInit(): void {
    }

}

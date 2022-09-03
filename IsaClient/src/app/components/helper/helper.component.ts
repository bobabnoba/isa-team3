import { Component, OnInit } from '@angular/core';
import { IAddressClass } from 'src/app/interfaces/address';

@Component({
  selector: 'app-helper',
  templateUrl: './helper.component.html',
  styleUrls: ['./helper.component.css']
})
export class HelperComponent implements OnInit {

  public address: IAddressClass = {
    id: 0,
    country: 'Srbija',
    city: 'Novi Sad',
    street: 'Kisacka 1',
    zipCode: 0,
    longitude: 19.833549,
    latitude: 45.267136
  };

  constructor() { }

  ngOnInit(): void {
  }

}

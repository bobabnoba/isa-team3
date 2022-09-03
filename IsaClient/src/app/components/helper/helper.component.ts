import { Component, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';

@Component({
  selector: 'app-helper',
  templateUrl: './helper.component.html',
  styleUrls: ['./helper.component.css']
})
export class HelperComponent implements OnInit {

  public address: IAddress = {
    id: 0,
    country: 'Srbija',
    city: 'Novi Sad',
    street: 'Branka Bajica 70A',
    zipCode: 0,
  };

  constructor() { }

  ngOnInit(): void {
  }

}

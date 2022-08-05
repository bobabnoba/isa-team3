import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IProfileView } from 'src/app/interfaces/rental-view';

@Component({
  selector: 'app-rental-view',
  templateUrl: './rental-view.component.html',
  styleUrls: ['./rental-view.component.css']
})
export class RentalViewComponent implements OnInit {

  @Input()
  item!: IProfileView
  constructor(private _router: Router) {
    console.log(this.item);
    
   }

  ngOnInit(): void {
  }
  seeMore() {

    var id = this.item.id;
    if (this.item.rentalType == "HOME") {
      this._router.navigate(['vacation/home/' + id]);
    }
    if (this.item.rentalType == "ADVENTURE") {
      this._router.navigate(['adventure/' + id]);
    }
    if (this.item.rentalType == "BOAT") {
      this._router.navigate(['boat/' + id]);
    }
  }
}

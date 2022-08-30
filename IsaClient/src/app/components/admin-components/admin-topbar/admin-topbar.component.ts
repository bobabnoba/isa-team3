import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-admin-topbar',
  templateUrl: './admin-topbar.component.html',
  styleUrls: ['./admin-topbar.component.css']
})
export class AdminTopbarComponent implements OnInit {

  @Output() searchInput : EventEmitter<string> = new EventEmitter();
  @Input() admin : string = ""
  constructor() { }

  ngOnInit(): void {
  }

  emitMe( searchText : any){
    this.searchInput.emit(searchText.target.value);
  }

}

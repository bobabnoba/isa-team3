import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-instructor-topbar',
  templateUrl: './instructor-topbar.component.html',
  styleUrls: ['./instructor-topbar.component.css']
})
export class InstructorTopbarComponent implements OnInit {

  @Output() searchInput : EventEmitter<string> = new EventEmitter();
  @Input() instructor : string = ""
  toggle : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  emitMe( searchText : any){
    this.searchInput.emit(searchText.target.value);
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }
}

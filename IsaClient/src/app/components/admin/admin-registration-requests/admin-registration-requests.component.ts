import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table'

@Component({
  selector: 'app-admin-registration-requests',
  templateUrl: './admin-registration-requests.component.html',
  styleUrls: ['./admin-registration-requests.component.css']
})
export class AdminRegistrationRequestsComponent implements OnInit, AfterViewInit  {

  displayedColumns: string[] = ['type', 'motivation', 'email', 'respond'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor() { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}


export interface PeriodicElement {
  type: string;
  motivation: string;
  email: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {type: "Boat Owner", motivation: 'I am so motivated. pls accept me zozozo I am so motivated. pls accept me zozozo, I am so motivated. pls accept me zozozo I am so motivated. pls accept me zozozo I am so motivated. pls accept me zozozo',
   email: 'ananas@gmail.com'},
  {type: 'Home Owner', motivation: 'Helium', email: 'rerere@gmail.com'},
  {type: 'Instructor', motivation: 'Lithium', email: 'sds@gmail.com'},
  {type: 'Instructor', motivation: 'Beryllium', email: 'asdrhh@gmail.com'},
];

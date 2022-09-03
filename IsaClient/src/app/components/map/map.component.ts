import { ThrowStmt } from '@angular/compiler';
import { AfterViewInit, Component, Input } from '@angular/core';
import * as L from 'leaflet';
import { IAddress, IAddressClass } from 'src/app/interfaces/address';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  @Input()
  address : IAddressClass;
  private map: L.Map | L.LayerGroup<any> | undefined;
  private centroid: L.LatLngExpression;

  constructor() {
    this.address = {} as IAddressClass;
    this.centroid = [this.address.latitude, this.address.longitude];
   }

  ngAfterViewInit(): void {
    this.initMap();
  }

  private initMap(): void {
    this.centroid = [this.address.latitude, this.address.longitude];
    this.map = L.map('map').setView([this.address.latitude, this.address.longitude], 13);

    // const tiles = L.tileLayer('https://tiles.wmflabs.org/hikebike/{z}/{x}/{y}.png', {
    //   maxZoom: 20,
    //   attribution: '&copy; <a href="https://stadiamaps.com/">Stadia Maps</a>, &copy; <a href="https://openmaptiles.org/">OpenMapTiles</a> &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors'
    // });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 20,
      attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors'
    });

    tiles.addTo(this.map);
    console.log(this.centroid);
    const marker = L.marker(this.centroid).addTo(this.map);
    marker.bindPopup(this.address.street + ', ' + this.address.city + ', ' + this.address.country).openPopup();
    marker.addTo(this.map);
  }

}

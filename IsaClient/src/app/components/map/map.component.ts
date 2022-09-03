import { ThrowStmt } from '@angular/compiler';
import { AfterViewInit, Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { da, fi } from 'date-fns/locale';
import * as L from 'leaflet';
import { IAddress } from 'src/app/interfaces/address';
import { MapService } from 'src/app/services/map-service/map.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit, OnChanges {
  @Input()
  address : IAddress;
  street: string;
  city: string;
  country: string;
  coordinates : [number, number] = [0, 0];
  private map: L.Map | L.LayerGroup<any> | undefined;
  centroid: L.LatLngExpression;

  constructor(private _mapService : MapService) { 
    this.address = {} as IAddress;
    this.centroid = [0, 0];
    this.street = '';
    this.city = '';
    this.country = '';
    
   }
   ngOnChanges(changes: SimpleChanges): void {
    this.initMap(changes.address.currentValue);
    
  }
   

  ngAfterViewInit(): void {
  }

   initMap(address : any): void {
    var fullAddress =  address.street + ', ' + address.city + ', ' + address.country;
    const observer = {
      next: (data : any) => { 
        console.log(data);
        if(data.length > 0) {
          this.centroid = [parseFloat(data[0].lat), parseFloat(data[0].lon)];        
       }else{
          this.centroid = [45.267136, 19.833549];
       }
       this.map?.off();
       this.map?.remove();
       this.map = L.map('map').setView(this.centroid, 13);
        const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          maxZoom: 20,
          attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors'
        });

        tiles.addTo(this.map);
        
        const marker = L.marker(this.centroid).addTo(this.map);
        marker.bindPopup(fullAddress).openPopup();
        marker.addTo(this.map);
        
      },
      error: (err : any) => { 
        console.log(err) },
      complete: () => {  },
    };

    this._mapService.getCoordinates(fullAddress).subscribe(observer);
   }


}

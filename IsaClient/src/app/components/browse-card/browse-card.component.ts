import { Component, Input, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { IUtility } from 'src/app/interfaces/vacation-house-profile';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-browse-card',
  templateUrl: './browse-card.component.html',
  styleUrls: ['./browse-card.component.css']
})
export class BrowseCardComponent implements OnInit {
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() address: IAddress = {
    id: 1,
    country: '',
    city: '',
    street: '',
    zipCode: 1
  };
  @Input() price: number = 0;
  @Input() rating: number = 0;
  @Input() ownerName: string = '';
  @Input() utilities: IUtility[] = [];
  @Input() imageUrls: string[] = [];
  @Input() imageUrl: string = '';
  image: any;
  @Input() id: number = 0;
  @Input() type: string = 'entity';
  role: string = ''
  baseUrl = environment.apiURL
  constructor(private _service: StorageService) {
    this.role = _service.getRole();
  }

  ngOnInit(): void { }

  preview() {
    if (this.role != 'ROLE_CLIENT') {
      window.location.href = '/unauth/' + this.type + '/page/' + this.id;
    }
    else {
      window.location.href = this.type + '/page/' + this.id;

    }
  }


}

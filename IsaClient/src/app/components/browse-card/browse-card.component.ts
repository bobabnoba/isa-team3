import { Component, Input, OnInit } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { IUtility } from 'src/app/interfaces/vacation-house-profile';

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
  image: any;
  @Input() id: number = 0;
  @Input() type: string = 'entity';

  constructor() { }

  ngOnInit(): void {
    if (this.imageUrls.length != 0) {
      this.image = this.imageUrls[0];
    } else {
      this.image = '../../../assets/images/beach-houses.jpg';
    }
  }
  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener(
      'load',
      () => {
        this.image = reader.result;
      },
      false
    );

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(id: number) {
    // this.imageService.getImage(id).subscribe((data) => {
    //   this.createImageFromBlob(data);
    // });
  }

  preview() {  
    window.location.href = '/' + this.type + '/' + this.id;
  }


}

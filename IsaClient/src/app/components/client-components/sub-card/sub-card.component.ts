import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IAddress } from 'src/app/interfaces/address';
import { IUnsub } from 'src/app/interfaces/unsub';


@Component({
  selector: 'app-sub-card',
  templateUrl: './sub-card.component.html',
  styleUrls: ['./sub-card.component.css']
})

export class SubCardComponent implements OnInit {
  @Output() clientUnsubscribe: EventEmitter<IUnsub> = new EventEmitter();
  @Input() name: string = '';
  @Input() ownerName: string = '';
  @Input() type: string = 'entity';
  @Input() id: number = 0;
  @Input() price: number = 0;
  @Input() address: IAddress = {
    id: 1,
    country: '',
    city: '',
    street: '',
    zipCode: 1
  };

  constructor() {
  }

  ngOnInit(): void {
  }
  preview() {
    window.location.href = '/' + this.type + '/page/' + this.id;
  }

  unsubscribe() {
    this.clientUnsubscribe.emit({
      id: this.id,
      type: this.type
    });
  }

}

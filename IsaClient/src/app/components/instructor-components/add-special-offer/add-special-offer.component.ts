import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Adventure } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { MTX_DATETIME_FORMATS } from '@ng-matero/extensions/core';
import { MtxCalendarView, MtxDatetimepickerMode, MtxDatetimepickerType } from '@ng-matero/extensions/datetimepicker';

@Component({
  selector: 'app-add-special-offer',
  templateUrl: './add-special-offer.component.html',
  styleUrls: ['./add-special-offer.component.css'], 
  providers: [
    {
      provide: MTX_DATETIME_FORMATS,
      useValue: {
        parse: {
          dateInput: 'YYYY-MM-DD',
          monthInput: 'MMMM',
          timeInput: 'HH:mm',
          datetimeInput: 'YYYY-MM-DD HH:mm',
        },
        display: {
          dateInput: 'YYYY-MM-DD',
          monthInput: 'MMMM',
          timeInput: 'HH:mm',
          datetimeInput: 'YYYY-MM-DD HH:mm',
          monthYearLabel: 'YYYY MMMM',
          dateA11yLabel: 'LL',
          monthYearA11yLabel: 'MMMM YYYY',
          popupHeaderDateLabel: 'MMM DD, ddd',
        },
      },
    },
  ],
})
export class AddSpecialOfferComponent implements OnInit {


  type: MtxDatetimepickerType = 'datetime';
  mode: MtxDatetimepickerMode = 'auto';
  startView: MtxCalendarView = 'month';
  multiYearSelector = false;
  touchUi = false;
  twelvehour = false;
  timeInterval = 1;

  x = new FormControl();

  adventures : Adventure[] = [];

  form = this._formBuilder.group({
    adventure: ['', Validators.required],
    activeUntil : [''],
    reservationDate : ['', Validators.required],
    guests:  ['', Validators.required],
    price:  ['', Validators.required],

  })

  constructor(private _formBuilder : FormBuilder, private _advService : AdventureService,
     private _storageService : StorageService) { }

  ngOnInit(): void {
    this._advService.getAllByInstructor(this._storageService.getEmail()).subscribe(
      res => {
        this.adventures = res;
      }
    )
  }

}

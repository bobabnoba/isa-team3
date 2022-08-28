import { Component, OnInit,  ChangeDetectionStrategy, Input, SimpleChanges} from '@angular/core';
import { isSameDay, isSameMonth } from 'date-fns';
import { Subject } from 'rxjs';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { BoatOwnerService } from 'src/app/services/boat-owner-service/boat-owner.service';
import { Router } from '@angular/router';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-availability-calendar-boat-owner',
    changeDetection: ChangeDetectionStrategy.OnPush,
  styles: [
    `
      h3 {
        margin: 0 0 10px;
      }

      pre {
        background-color: #f5f5f5;
        padding: 15px;
      }
    `,
  ],
  templateUrl: './availability-calendar-boat-owner.component.html',
  styleUrls: ['./availability-calendar-boat-owner.component.css']
})
export class AvailabilityCalendarBoatOwnerComponent implements OnInit {

  @Input()
  newAv! : any;

  @Input()
  boatOwnerEmail! : string;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  refresh = new Subject<void>();

  events: CalendarEvent[] = [
  ];

  activeDayIsOpen: boolean = false;

  constructor(private _boatService : BoatService,private _boatOwnerService : BoatOwnerService,
     private _storageService: StorageService, private _router : Router) {
    this.newAv = {};
  }

  ngOnChanges(changes: SimpleChanges): void {
    // now aw is changed 
    if(this.newAv !== undefined){
      if( changes.newAv.currentValue){
      this.addEvent(changes.newAv.currentValue.startDate, changes.newAv.currentValue.endDate);
      }
      } 
  }

  ngOnInit(): void {
      console.log(this.boatOwnerEmail);
      this._boatOwnerService.getBoatOwner(this._storageService.getEmail()).subscribe(
        (data) => {
          data.availability.forEach((e : any) => 
            this.addEvent(e.startDate, e.endDate)
          )
        }
      );
    
    
  }
  
  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  addEvent(sd : any, ed : any): void {
    this.events = [
      ...this.events,
      {
        title: 'Available day',
        start: (new Date(sd)),
        end: (new Date(ed)),
        color: colors.blue,
        allDay: true,
      },
    ];
    this.refresh.next();
  }


  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

}

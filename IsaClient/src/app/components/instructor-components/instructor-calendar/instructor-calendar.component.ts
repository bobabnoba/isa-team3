import { Component, OnInit,  ChangeDetectionStrategy, Input, OnChanges, SimpleChanges} from '@angular/core';
import { isSameDay, isSameMonth } from 'date-fns';
import { Subject } from 'rxjs';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { DatePipe } from '@angular/common';
import { AdventureReservationInfo } from 'src/app/interfaces/adventure-reservation-info';
import { AdventureOfferInfo } from 'src/app/interfaces/adventure-offer-info';

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
  green : {
    primary: '#33cc33',
    secondary: '#adebad',
  },
  magenta : {
    primary: '#990099',
    secondary: '#ffb3ff'
  },
  purple: {
    primary: '#5200cc',
    secondary: '#c299ff'
  }
};

@Component({
  selector: 'app-instructor-calendar',
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
  templateUrl: './instructor-calendar.component.html',
  styleUrls: ['./instructor-calendar.component.css']
})
export class InstructorCalendarComponent implements OnInit, OnChanges {

  @Input()
  newAv! : any;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  refresh = new Subject<void>();

  events: CalendarEvent[] = [
  ];

  activeDayIsOpen: boolean = false;

  constructor(private _instructorService : InstructorService, private _storageService: StorageService, private _datePipe : DatePipe) {}

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if( changes.newAv.currentValue){
      this.events = [... this.events.filter(e => e.title != 'Available')]
      changes.newAv.currentValue.forEach((e : any) => {
        this.addEvent(e.startDate, e.endDate)
      })
    }
  }

  ngOnInit(): void {
    this._instructorService.getInstructor(this._storageService.getEmail()).subscribe(
      (data) => {
        data.availability.forEach((e : any) => 
          this.addEvent(e.startDate, e.endDate)
        )
      }
    );

    this._instructorService.getAllReservations(this._storageService.getEmail()).subscribe(
      (data) => {
        data.forEach((e : any) => 
          this.addReservationEvent(e)
        )
      }
    );

    this._instructorService.getAllSpecialOffers(this._storageService.getEmail()).subscribe(
      (data) => {
        data.forEach((e : any) => 
          this.addSpecialOfferEvent(e)
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
        title: 'Available',
        start: (new Date(sd)),
        end: (new Date(ed)),
        color: colors.blue,
        allDay: false,
      },
    ];
    this.refresh.next();
  }

  addReservationEvent(resInfo : AdventureReservationInfo): void {
    this.events = [
      ...this.events,
      {
        title: 'Booked adventure ' + '<i>' + resInfo.title + '</i>' + '  from ' + this._datePipe.transform(new Date(resInfo.startDate), 'HH:mm') +
         ' to ' + this._datePipe.transform(new Date(resInfo.endDate), 'HH:mm') + " by " + resInfo.firstName + ' ' + resInfo.lastName + ' (' + resInfo.email + ')',
        start: (new Date(resInfo.startDate)),
        end: (new Date(resInfo.endDate)),
        color: colors.magenta,
        allDay: false,
      },
    ];
    this.refresh.next();
  }

  addSpecialOfferEvent(so : AdventureOfferInfo): void {
    this.events = [
      ...this.events,
      {
        title: 'Special offer for adventure ' + '<i>' + so.title + '</i>' +  '  from ' + this._datePipe.transform(new Date(so.reservationStartDate), 'HH:mm') +
        ' to ' + this._datePipe.transform(new Date(so.reservationEndDate), 'HH:mm'),
        start: (new Date(so.reservationStartDate)),
        end: (new Date(so.reservationEndDate)),
        color: colors.green,
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

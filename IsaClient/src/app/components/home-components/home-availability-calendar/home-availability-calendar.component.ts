import { ChangeDetectionStrategy, Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { isSameDay, isSameMonth } from 'date-fns';
import { Subject } from 'rxjs';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { Router } from '@angular/router';
import { Reservation } from 'src/app/interfaces/reservation';
import { SpecialOffer } from 'src/app/interfaces/special-offer';
import { DatePipe } from '@angular/common';

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
  selector: 'app-home-availability-calendar',
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
  templateUrl: './home-availability-calendar.component.html',
  styleUrls: ['./home-availability-calendar.component.css']
})
export class HomeAvailabilityCalendarComponent implements OnInit {

  @Input()
  newAv! : any;

  @Input()
  home! : VacationHome;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  refresh = new Subject<void>();

  events: CalendarEvent[] = [
  ];

  activeDayIsOpen: boolean = false;

  constructor(private _homeService : HomeService, private _datePipe : DatePipe,
     private _storageService: StorageService, private _router : Router) {
    this.newAv = {};
    this.home = {} as VacationHome;
  }

  ngOnChanges(changes: SimpleChanges): void {
    // now aw is changed 
    if(this.newAv !== undefined){
      if( changes.newAv.currentValue){
        this.events = [... this.events.filter(e => e.title != 'Available')]
        changes.newAv.currentValue.forEach((e : any) => {
          this.addEvent(e.startDate, e.endDate)
        })
      }
      } 
  }

  ngOnInit(): void {
      this._homeService.getById(this._router.url.substring(26)).subscribe(
        (data) => {
          data.availability.forEach((e : any) => {
            this.addEvent(e.startDate, e.endDate)}
          )
          data.specialOffers.forEach((e : any) => {
            this.addSpecialOfferEvent(e)})
        }     
      );

      this._homeService.getHomeReservations(this._router.url.substring(26)).subscribe(
        data => {
          data.forEach((e : any) => {
            this.addReservationEvent(e)}
          )
        }
      )
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
        allDay: true,
      },
    ];
    this.refresh.next();
  }

  addSpecialOfferEvent(offer : SpecialOffer){
    this.events = [
      ...this.events,
      {
        title: 'Special offer' +   
        '  from ' + this._datePipe.transform(new Date(offer.reservationStartDate), 'MMM d, y, h:mm') +
        ' to ' + this._datePipe.transform(new Date(offer.reservationEndDate), 'MMM d, y, h:mm'),
        start: (new Date(offer.reservationStartDate)),
        end: (new Date(offer.reservationEndDate)),
        color: colors.green,
        allDay: true,
      },
    ];
    this.refresh.next();
  }

  addReservationEvent(resInfo : Reservation): void {
    console.log(resInfo)
    this.events = [
      ...this.events,
      {
        title: 'Booked  from ' + this._datePipe.transform(new Date(resInfo.startDate), 'MMM d, y, h:mm') +
         ' to ' + this._datePipe.transform(new Date(resInfo.endDate), 'MMM d, y, h:mm') ,
        start: (new Date(resInfo.startDate)),
        end: (new Date(resInfo.endDate)),
        color: colors.purple,
        allDay: false,
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

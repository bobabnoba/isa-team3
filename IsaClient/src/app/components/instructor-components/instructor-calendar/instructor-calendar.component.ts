import { Component, OnInit,  ChangeDetectionStrategy, Input, OnChanges, SimpleChanges} from '@angular/core';
import { isSameDay, isSameMonth } from 'date-fns';
import { Subject } from 'rxjs';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

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

  constructor(private _instructorService : InstructorService, private _storageService: StorageService) {}

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if( changes.newAv.currentValue){
      this.events = [];
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

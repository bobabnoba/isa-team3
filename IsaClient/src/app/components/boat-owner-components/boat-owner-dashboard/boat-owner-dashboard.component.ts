import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';
import { BoatOwnerService } from 'src/app/services/boat-owner-service/boat-owner.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-boat-owner-dashboard',
  templateUrl: './boat-owner-dashboard.component.html',
  styleUrls: ['./boat-owner-dashboard.component.css']
})
export class BoatOwnerDashboardComponent implements OnInit {

  email : string = '';
  labelsWeek: any[] = [];
  valuesWeek: number[] = [];
  labelsMonth: any[] = [];
  valuesMonth: number[] = [];
  labelsYear: any[] = [];
  valuesYear: number[] = [];
  constructor(private _boatOwnerService : BoatOwnerService, private _pipe : DatePipe,
     private _reservationService : ReservationService, private _storageService: StorageService) {
    this.email = this._storageService.getEmail();
   }

  ngOnInit(): void {
    var now = new Date();
    this._reservationService.getReservationChartForDateRange(
      this._pipe.transform( new Date(new Date(now).setDate(now.getDate() - 7)), 'yyyy-MM-dd')!, 
      this._pipe.transform( now, 'yyyy-MM-dd')!,
      '100',
      "BOAT").subscribe(
        res => {
          console.log(res);
          res.forEach(earning => {  
            this.labelsWeek.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
            this.valuesWeek.push(earning.income);
          })
          
          console.log(this.labelsWeek);
          console.log(this.valuesWeek);

          const data = {
            labels: this.labelsWeek,
            datasets: [{
              label: 'Chart for your reservations this week ',
              backgroundColor: 'rgb(255, 99, 132)',
              borderColor: 'rgb(255, 99, 132)',
              data: this.valuesWeek,
            }]
          };
          
            const myChart = new Chart(
              'myChartWeek',
              {
                type: 'bar',
                data: data,
                options: {}
              }
            );
          })

          this._reservationService.getReservationChartForDateRange(
            this._pipe.transform(new Date(now.getFullYear(), now.getMonth(), 1), 'yyyy-MM-dd')!, 
            this._pipe.transform(now, 'yyyy-MM-dd')!,
            '100',
            "BOAT").subscribe(
              res => {
                console.log(res);
                res.forEach(earning => {  
                  this.labelsMonth.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
                  this.valuesMonth.push(earning.income);
                })

                console.log(this.labelsMonth);
                console.log(this.valuesMonth);
                
                const data = {
                  labels: this.labelsMonth,
                  datasets: [{
                    label: 'Chart for your reservations this month',
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: this.valuesMonth,
                  }]
                };
                
                  const myChart = new Chart(
                    'myChartMonth',
                    {
                      type: 'bar',
                      data: data,
                      options: {}
                    }
                  );
                })
                this._reservationService.getReservationChartForDateRangeYear(
                  this._pipe.transform(new Date(now.getFullYear(), 1, 1), 'yyyy-MM-dd')!, 
                  this._pipe.transform(now, 'yyyy-MM-dd')!,
                  '100',
                  "BOAT").subscribe(
                    res => {
                      console.log(res);
                      res.forEach(earning => {  
                        this.labelsYear.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
                        this.valuesYear.push(earning.income);
                      })

                      console.log(this.labelsYear);
                      console.log(this.valuesYear);
                      
                      const data = {
                        labels: this.labelsYear,
                        datasets: [{
                          label: 'Chart for your reservations this year',
                          backgroundColor: 'rgb(255, 99, 132)',
                          borderColor: 'rgb(255, 99, 132)',
                          data: this.valuesYear,
                        }]
                      };
                      
                        const myChart = new Chart(
                          'myChartYear',
                          {
                            type: 'bar',
                            data: data,
                            options: {}
                          }
                        );
                      })

  
  }

}

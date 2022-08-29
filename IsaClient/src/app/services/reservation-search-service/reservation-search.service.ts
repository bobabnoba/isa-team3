import { Injectable } from '@angular/core';
import { IReservation } from 'src/app/interfaces/new-reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationSearchService {
  constructor() {}

  filter(entities: any[], sortType: string) {
    switch (sortType) {
      case 'PRICE_LOWER':
        return this.priceLower(entities);
      case 'PRICE_HIGHER':
        return this.priceHigher(entities);
      case 'START_HIGHER':
        return this.startHigher(entities);
      case 'START_LOWER':
        return this.startLower(entities);
      case 'DURATION_HIGHER':
        return this.durationHigher(entities);
      case 'DURATION_LOWER':
        return this.durationLower(entities);
      default:
        return entities;
    }
  }

  priceHigher(entities: IReservation[]) {
    return entities.sort((n1, n2) => {
      if (n1.price < n2.price) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  priceLower(entities: IReservation[]) {
    return entities.sort((n1, n2) => {
      if (n1.price > n2.price) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  startLower(entities: IReservation[]) {
    return entities.sort((n1, n2) => {
      if (n1.startDate < n2.startDate) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  startHigher(entities: IReservation[]) {
    return entities.sort((n1, n2) => {
      if (n1.startDate > n2.startDate) {
        return 1;
      } else {
        return -1;
      }
    });
  }



  durationLower(entities: IReservation[]) {
    return entities.sort((n1, n2) => {
      if (
        this.getDateDifference(n1.startDate, n1.endDate) >
        this.getDateDifference(n2.startDate, n2.endDate)
      ) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  durationHigher(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (
        this.getDateDifference(n1.startDate, n1.endDate) <
        this.getDateDifference(n2.startDate, n2.endDate)
      ) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  getDateDifference(date1: Date | string, date2: Date | string) {
    date1 = new Date(date1);
    date2 = new Date(date2);
    let dayDifference =
      1 + Math.floor((date2.getTime() - date1.getTime()) / 1000 / 60 / 60 / 24);
    return Math.abs(dayDifference);
  }
}

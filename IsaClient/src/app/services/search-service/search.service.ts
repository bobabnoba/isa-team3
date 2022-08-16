import { Injectable } from '@angular/core';
import { SearchFilter } from 'src/app/filters/search-filter';
import { IAddress } from 'src/app/interfaces/address';
import { IFilter } from 'src/app/interfaces/filter';
import { IProfileView } from 'src/app/interfaces/rental-view';
import { InstructorBrowse } from '../../interfaces/instructor-browse';
import { IUtility, IVacationHouseProfile } from '../../interfaces/vacation-house-profile';


@Injectable({
  providedIn: 'root'
})
export class SearchService {
 

  constructor() { }

  filter(items: IVacationHouseProfile[], filter: SearchFilter) {
    var retVal: IVacationHouseProfile[] = [];

    for (let item of items) {
      if (
        this.matchName(item.name, filter.text) ||
        this.matchName(item.address.city, filter.text) ||
        this.matchName(item.address.country, filter.text) ||
        this.matchName(item.address.street, filter.text)
      ) {
        let flag = true;
        for (let tag of filter.tags) {
          if (!this.hasTag(tag, item.utilities)) flag = false;
        }

        if (flag) retVal.push(item)
      }
    }


    switch (filter.sort) {
      case 'PRICE_LOWER':
        return this.priceLower(retVal);
      case 'PRICE_HIGHER':
        return this.priceHigher(retVal);
      case 'RATING_HIGHER':
        return this.ratingHigher(retVal);
      case 'RATING_LOWER':
        return this.ratingLower(retVal);
      case 'NAME_ACS':
        return this.nameAcs(retVal);
      case 'NAME_DES':
        return this.nameDes(retVal);
      case 'ADDR_ACS':
        return this.addrAcs(retVal);
      case 'ADDR_DES':
        return this.addrDes(retVal);
      default:
        return retVal;
    }
  }
  hasTag(tag: string, utilities: IUtility[]) {
    let tagValidation = false;
    for (let utility of utilities) {
      if (utility.name == tag) return true;
    }
    return tagValidation;
  }
  matchName(string1: string, string2: string) {

    string1 = string1.toLowerCase().replace(/\s/g, '');
    string2 = string2.toLowerCase().replace(/\s/g, '');
    return string1.includes(string2) || string2 == '';
  }
  priceHigher(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.pricePerDay < n2.pricePerDay) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  priceLower(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.pricePerDay > n2.pricePerDay) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  ratingLower(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.rating > n2.rating) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  ratingHigher(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.rating < n2.rating) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  nameAcs(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.name > n2.name) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  nameDes(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.name < n2.name) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  addrAcs(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.address.country > n2.address.country) {
        return 1;
      } else {
        return -1;
      }
    });
  }

  addrDes(entities: any[]) {
    return entities.sort((n1, n2) => {
      if (n1.address.country < n2.address.country) {
        return 1;
      } else {
        return -1;
      }
    });
  }
  filterInstructors(items: InstructorBrowse[], filter: SearchFilter) {
    var retVal: InstructorBrowse[] = [];

    for (let item of items) {
      if (this.matchRating(item.rating, filter.rating)) {
        if (
          //Match search box-a
          this.matchName(item.firstName, filter.text) ||
          this.matchName(item.lastName, filter.text) ||
          this.matchAddress(item.address, filter.text) ||
          this.matchName(item.biography, filter.text) ||
          this.matchName(item.email, filter.text)
        ) {


          retVal.push(item)
        }
      }
    }

    switch (filter.sort) {

      case 'RATING_HIGHER':
        return this.ratingHigher(retVal);
      case 'RATING_LOWER':
        return this.ratingLower(retVal);
      case 'NAME_ACS':
        return this.nameAcs(retVal);
      case 'NAME_DES':
        return this.nameDes(retVal);
      case 'ADDR_ACS':
        return this.addrAcs(retVal);
      case 'ADDR_DES':
        return this.addrDes(retVal);
      default:
        return retVal;
    }

  }

  filterProfiles(items: IProfileView[], filter: SearchFilter) {
    var retVal: IProfileView[] = [];

    for (let item of items) {

      // if (this.matchType(item.rentalType, filter.type)) 
        //Match rating
        if (this.matchRating(item.rating, filter.rating)) {
          if (
            //Match search box-a
            this.matchName(item.name, filter.text) ||
            this.matchAddress(item.address, filter.text) ||
            this.matchName(item.owner.firstName, filter.text) ||
            this.matchName(item.pricePerDay.toString(), filter.text)

          ) {

            //Match utilities
            let flag = true;
            for (let tag of filter.tags) {
              if (!this.hasTag(tag, item.utilities)) flag = false;
            }

            if (flag) retVal.push(item)
          
        }
      }
    }

    switch (filter.sort) {
      case 'PRICE_LOWER':
        return this.priceLower(retVal);
      case 'PRICE_HIGHER':
        return this.priceHigher(retVal);
      case 'RATING_HIGHER':
        return this.ratingHigher(retVal);
      case 'RATING_LOWER':
        return this.ratingLower(retVal);
      case 'NAME_ACS':
        return this.nameAcs(retVal);
      case 'NAME_DES':
        return this.nameDes(retVal);
      case 'ADDR_ACS':
        return this.addrAcs(retVal);
      case 'ADDR_DES':
        return this.addrDes(retVal);
      default:
        return retVal;
    }
  }
  matchRating(rating: number, filterRating: number): boolean {
    if (rating >= filterRating) {
      return true
    }
    return false
  }
  matchAddress(item: IAddress, text: string): boolean {
    if (item != null) {

      if (this.matchName(item.city, text) ||
        this.matchName(item.country, text) ||
        this.matchName(item.street, text)) {
        return true;

      }
      return false;
    }
    return false;
  }
  matchType(rentalType: string, types: string[]): boolean {
    var retVal = false;

    for (let firstarray of types) {


      retVal = this.matchName(rentalType, firstarray)
      if (retVal == true) {

        return retVal
      }
    }

    return retVal
  }

}

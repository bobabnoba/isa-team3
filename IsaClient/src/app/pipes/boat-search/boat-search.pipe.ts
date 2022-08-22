import { Pipe, PipeTransform } from '@angular/core';
import { Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';

@Pipe({
  name: 'boatSearch'
})
export class BoatSearchPipe implements PipeTransform {

  transform(searchList : Array<Boat>, search : string): any {

    searchList.forEach(boat => {
      boat = {} as Boat;
      boat.utilities = [] as Utility[];
      boat.utilities.forEach(utility => { utility = { } as Utility })
    })

    let retVal = searchList as Boat[];

  if (searchList && search){
      retVal = searchList.filter(
        (d) =>
          d.name.toLowerCase()
        .indexOf(search.toLowerCase()) > -1 ||

          d.description.toLowerCase()
          .indexOf(search.toLowerCase()) > -1 ||

          d.pricePerDay === parseInt(search) ||

          d.guestLimit === parseInt(search) 
      
      );

      searchList.forEach(d => {
        d.utilities.forEach( ut => {
          if (search.toLowerCase().includes(ut.name.toLowerCase())) {
            retVal.push(d)
          }
         })
      });

      searchList.forEach(d => {
        d.fishingEquipment.forEach( ut => {
          if (search.toLowerCase().includes(ut.name.toLowerCase())) {
            retVal.push(d)
          }
         })
      })  
  }
    return [... new Set(retVal)];
  }

}

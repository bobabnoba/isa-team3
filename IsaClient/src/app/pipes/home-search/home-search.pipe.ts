import { Pipe, PipeTransform } from '@angular/core';
import { Utility } from 'src/app/interfaces/adventure';
import { VacationHome } from 'src/app/interfaces/vacation-home';

@Pipe({
  name: 'homeSearch'
})
export class HomeSearchPipe implements PipeTransform {

  transform(searchList : Array<VacationHome>, search : string): any {

    searchList.forEach(home => {
      home = {} as VacationHome;
      home.utilities = [] as Utility[];
      home.utilities.forEach(utility => { utility = { } as Utility })
    })

    let retVal = searchList as VacationHome[];

  if (searchList && search){
      retVal = searchList.filter(
        (d) =>
          d.name.toLowerCase()
        .indexOf(search.toLowerCase()) > -1 ||

          d.description.toLowerCase()
          .indexOf(search.toLowerCase()) > -1 ||

          d.pricePerDay === parseInt(search) ||

          d.guestLimit === parseInt(search)  ||

          d.rooms.length === parseInt(search) 
      
      );

      searchList.forEach(d => {
        d.utilities.forEach( ut => {
          if (search.toLowerCase().includes(ut.name.toLowerCase())) {
            retVal.push(d)
          }
         })
      });

        
  }
    return [... new Set(retVal)];
  }


}

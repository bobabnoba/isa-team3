import { Pipe, PipeTransform } from '@angular/core';
import { Adventure, Utility } from 'src/app/interfaces/adventure';

@Pipe({
  name: 'adventureSearch'
})
export class AdventureSearchPipe implements PipeTransform {

  transform(searchList : Array<Adventure>, search : string): any {


    console.log('transform je, dobio sam ' + search);

    searchList.forEach(adventure => {
      adventure = {} as Adventure;
      adventure.utilities = [] as Utility[];
      adventure.utilities.forEach(utility => { utility = { } as Utility })
    })

    let retVal = searchList as Adventure[];

  if (searchList && search){
      retVal = searchList.filter(
        (d) =>
          d.title.toLowerCase()
        .indexOf(search.toLowerCase()) > -1 ||

          d.description.toLowerCase()
          .indexOf(search.toLowerCase()) > -1 ||

          d.pricePerDay === parseInt(search) ||

          d.maxNumberOfParticipants === parseInt(search) 
      
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

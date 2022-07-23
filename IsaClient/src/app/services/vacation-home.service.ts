import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { IVacationHome } from '../interfaces/vacation-home';

@Injectable({
  providedIn: 'root'
})
export class VacationHomeService {
  constructor(private http: HttpClient) { }
  createVacationHome(newVacationHome: IVacationHome) {
    return this.http.post(
      'http://localhost:8090/home/',
      newVacationHome
    )
    .pipe(map((data: any) => {

      //if (!settings.hideLoader)
      //  this.showLoader(false);
      //handle api 200 response code here or you wanted to manipulate to response
      return data;

    })
      ,
      catchError((error) => {    // handle error
        console.log("error.status", error.status)
        if (error.status == 404) {
          //Handle Response code here
        }
        //if (!settings.hideLoader)
          //this.showLoader(false);

        return throwError(error);


      })
    );
  }

  addHome(newHome: FormData) {
    return this.http.post<IVacationHome>("http://localhost:8090/home/addNew", newHome);
  }
}

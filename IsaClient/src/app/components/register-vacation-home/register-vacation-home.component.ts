import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { catchError, map, throwError } from 'rxjs';
import { IVacationHome } from 'src/app/interfaces/vacation-home';
import { VacationHomeService } from 'src/app/services/vacation-home.service';

@Component({
  selector: 'app-register-vacation-home',
  templateUrl: './register-vacation-home.component.html',
  styleUrls: ['./register-vacation-home.component.css']
})
export class RegisterVacationHomeComponent implements OnInit {

  form!: FormGroup;
  newHome! : IVacationHome;
  cretedHome! : IVacationHome;
  description! : string;
  information! : string;
  codeOfConduct! : string;
  constructor(private http: HttpClient,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router,
    private vacationHomeService : VacationHomeService) { 
      this.newHome = {} as IVacationHome;
      this.cretedHome = {}  as IVacationHome;
    }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      address: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      description: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      codeOfConduct: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      information: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ])});
  }

  //SLIKEEEEE

  makeRequest(url: string, form: any, id : number, settings: any = { toast: true, hideLoader: false }) {

    let formData = form;
    const uploadData = new FormData();
    for (let i in form) {
      if (form[i] instanceof Blob) // check is file
        uploadData.append(i, form[i], form[i].name ? form[i].name : "");
      else if (form[i] instanceof Array) { //check type of file

        for (let arr in form[i]) {
          for (let lst in form[i][arr]) {
            if (form[i][arr][lst] instanceof Blob) { // check is file
              console.log("" + i + "[" + arr + "][" + lst + "]");
              uploadData.append("" + i + "[" + arr + "][" + lst + "]", form[i][arr][lst], form[i][arr][lst].name ? form[i][arr][lst].name : "");
            }
            else {
              uploadData.append("" + i + "[" + arr + "][" + lst + "]", form[i][arr][lst]);
            }
          }
        }
      }
      else
        uploadData.append(i, form[i]);
    }
    formData = uploadData



    formData.headers = new HttpHeaders({
    }).set('Content-Type', []);;



    if (!settings.hideLoader)
      this.showLoader(true);  // show loader


    return this.http.post(url, {formData, id}, { headers: formData.headers })
      .pipe(map((data: any) => {

        if (!settings.hideLoader)
          this.showLoader(false);
        //handle api 200 response code here or you wanted to manipulate to response
        return data;

      })
        ,
        catchError((error) => {    // handle error
          console.log("error.status", error.status)
          if (error.status == 404) {
            //Handle Response code here
          }
          if (!settings.hideLoader)
            this.showLoader(false);

          return throwError(error);


        })
      );

  }

  showLoader(show : any) {
    //loader code goes here
  }

  doDescriptionValueChange(ev: any) {
    this.description = ev.target.value;
  }
  doInformationValueChange(ev: any) {
    this.information = ev.target.value;
  }
  doCodeOfConductValueChange(ev: any) {
    this.codeOfConduct = ev.target.value;
  }

 
  onSubmit() {
    
    console.log('usao u submit');
    this.createVacationHome();
    this.form.value.pictures=this.files;
    console.log('usao u submit');
    this.vacationHomeService.createVacationHome(this.newHome).subscribe(data => {
      this.cretedHome.address = data;
      console.log(this.createVacationHome);
    });
    console.log(this.createVacationHome);
    this.makeRequest("http://localhost:8090/home/images", this.form,this.cretedHome.id).subscribe(data => {
      
      //handle response
    });
    this.router.navigate(['/home/' + this.cretedHome.id]);
  }
  createVacationHome() {
    this.newHome.address = this.form.value.address;
    this.newHome.name = this.form.value.name;
    this.newHome.description = this.description;
    this.newHome.information = this.information;
    this.newHome.codeOfConduct = this.codeOfConduct;
   // this.newHome.images = this.files;
  }

  files : any[] = [] 
  setFiles(e : Event) {
    const target = e.target as HTMLInputElement;
    const allFiles = target.files!;
    let extensionAllowed : any = { "png": true, "jpeg": true, "jpg" : true } ;
    let files = [];
    for (let file of allFiles) {
      if (file.size / 1024 / 1024 > 20) {
        alert("File size should be less than 20MB")
        return;
      }
      if (extensionAllowed) {
        var nam = file.name.split('.').pop();
        if (!extensionAllowed[nam!]) {
          alert("Please upload " + Object.keys(extensionAllowed) + " file.")
          return;
        }
      }
      files.push(file);

    }
    console.log(files);
   
    this.files=files;
  }

  //SLIKEEE

}

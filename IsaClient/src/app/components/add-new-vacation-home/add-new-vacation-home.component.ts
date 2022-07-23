import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Sanitizer } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FileHandle } from 'src/app/interfaces/file-handle';
import { IVacationHome } from 'src/app/interfaces/vacation-home';
import { VacationHomeService } from 'src/app/services/vacation-home.service';

@Component({
  selector: 'app-add-new-vacation-home',
  templateUrl: './add-new-vacation-home.component.html',
  styleUrls: ['./add-new-vacation-home.component.css']
})
export class AddNewVacationHomeComponent implements OnInit {

  
  form!: FormGroup;
  newHome! : IVacationHome;
  description! : string;
  information! : string;
  codeOfConduct! : string;
  constructor(private http: HttpClient,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router,
    private vacationHomeService : VacationHomeService,
    private sanitizer: DomSanitizer) { 
      this.newHome = {
        address:'AAA',
        codeOfConduct: '',
        description: '',
        id:0,
        information:'',
        name: '',
        images: []
      };
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

  addHome(f: NgForm){
    this.newHome.address = this.form.value.address
    this.newHome.name = this.form.value.name
    this.newHome.codeOfConduct = this.form.value.codeOfConduct
    this.newHome.description = this.form.value.description
    this.newHome.information = this.form.value.information
    
    const homeFormData = this.prepareFormData(this.newHome);
    this.vacationHomeService.addHome(homeFormData).subscribe(
      (response : IVacationHome) => {
        //this.f.reset();
        console.log(response.id)
        //TODO: redirekt na stranicu sa svim mojim vikendicama npr
        this.router.navigate(['/']);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    )
  }

  prepareFormData(home : IVacationHome) : FormData{
    const formData = new FormData();

    formData.append(
      'home',
      new Blob([JSON.stringify(home)], {type: 'application/json'})
    );
    
    for(var i = 0; i < home.images.length; i++){
      formData.append(
        'imageFile',
        home.images[i].file,
        home.images[i].file.name
      );
    }

    return formData;
    
  }

  onFileSelected(event : any) {
    if(event.target.files){
      const file = event.target.files[0];

      const fileHandler: FileHandle = {
        file: file,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(file))
      }
      this.newHome.images.push(fileHandler);
    }
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

}

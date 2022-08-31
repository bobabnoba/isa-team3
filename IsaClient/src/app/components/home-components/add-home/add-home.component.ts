import { HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { Utility } from 'src/app/interfaces/adventure';
import { Room } from 'src/app/interfaces/room';
import { Rule } from 'src/app/interfaces/rule';
import { VacationHome } from 'src/app/interfaces/vacation-home';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { HomeService } from 'src/app/services/vacation-home-service/home.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-home',
  templateUrl: './add-home.component.html',
  styleUrls: ['./add-home.component.css']
})
export class AddHomeComponent implements OnInit {

  existingServices! : Utility[]
  existingRules! : Rule[]
  existingRooms!: Room[];

  services : Utility[] = []
  rules : Rule[] = []
  rooms : Room[] = []
  newHome : VacationHome = {} as VacationHome
  home : VacationHome = {} as VacationHome
  dataHome : VacationHome = {} as VacationHome
  // typeSelect : string = '' as string;

  showImgUpload : boolean = false
  requestUrl! : string

  editMode : boolean = false
  homeId! : string

  servi = new FormControl()
  rul = new FormControl()
  newRoom = new FormControl()
  //ro= new FormControl()

  headers = new HttpHeaders()
    .set('Access-Control-Allow-Origin', '*')
    .set('Access-Control-Allow-Headers', '*')

  info = this._formBuilder.group({
    name: ['', Validators.required],
    pricePerDay: ['', Validators.required],
    description: ['', Validators.required],
    information: ['', Validators.required],
    guestsLimit: ['', Validators.required],
    cancelingPercentage: ['', Validators.required],

  })

  additional = this._formBuilder.group({
    serviceName : ['', Validators.required],
    servicePrice : ['', Validators.required],
    roomNumberOfBeds : ['', Validators.required],
  })

  conduct = this._formBuilder.group({
    rules : ['', Validators.required],
  })

  location = this._formBuilder.group({
    street: ['', Validators.required],
    city : ['', Validators.required],
    country : ['', Validators.required],
    zipCode:  ['', Validators.required],
  })

  images = this._formBuilder.group({ 
    image : ['', Validators.required],
  })

  constructor(private _formBuilder: FormBuilder, private _storageService : StorageService,
              private _homeService : HomeService, 
              public dialogRef: MatDialogRef<AddHomeComponent>,
              private _snackBar : MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public data: any) {

                this.newHome.address = {} as IAddress

                if(data.editMode)
                {
                  this.homeId = data.homeId;
                  this.editMode = true;
                  this.requestUrl =  environment.apiURL + '/vacation/homes/image-upload/' + this.homeId
                  this._homeService.getById(this.homeId).subscribe(
                    res => {
                      this.home = res;
                      this.info.setValue({
                        name : this.home.name,
                        pricePerDay : res.pricePerDay,
                        description : res.description,
                        information : res.information,
                        guestsLimit : res.guestLimit,
                        cancelingPercentage : res.cancelingPercentage,
                      })
                      this.location.setValue({
                        street : res.address.street,
                        city : res.address.city,
                        country : res.address.country,
                        zipCode : res.address.zipCode,
                      })
                      this.rules = res.codeOfConduct;
                      this.services = res.utilities;
                      this.rooms = res.rooms;
                      
                    }
                   )
                   
                }
               }

  ngOnInit(): void {
    
    
    this._homeService.getUtilities().subscribe(
      data => {
        this.existingServices = data;
      }
    )

    this._homeService.getCodeOfConduct().subscribe(
      data => {
        this.existingRules = data;
      }
    ) 
  }

  compareObjects(o1: any, o2: any) {
    if(o1.name == o2.name && o1.id == o2.id )
    return true;
    else return false
  }

  createHome() : any {
    this.createObject();
    this._homeService.addHome(this.newHome).subscribe(
      data => {
        this.dataHome = data;
        this.showImgUpload = true;
        this.requestUrl =  environment.apiURL + '/vacation/homes/image-upload/' + data.id;
        this.homeId = String(data.id);
      }
    );
  }

  createObject() : void {

    this.newHome.name = this.info.value.name;

    this.newHome.address.street = this.location.value.street,
    this.newHome.address.city =  this.location.value.city;
    this.newHome.address.country = this.location.value.country;
    this.newHome.address.zipCode = this.location.value.zipCode;
    this.newHome.description = this.info.value.description;
    this.newHome.pricePerDay = this.info.value.pricePerDay;
    this.newHome.information = this.info.value.information;
    this.newHome.cancelingPercentage = this.info.value.cancellationPercentage;
    this.newHome.guestLimit = this.info.value.guestsLimit;
    this.newHome.codeOfConduct = this.rules;
    this.newHome.ownerEmail = this._storageService.getEmail();
    this.newHome.utilities = this.services;
    this.newHome.rooms = this.rooms;

  }

  updateInfo(){
    this._homeService.updateInfo(this.homeId,   
      {
        name : this.info.value.name,
        pricePerDay : this.info.value.pricePerDay,
        description : this.info.value.description,
        information : this.info.value.information,
        guestLimit : this.info.value.guestsLimit,
        cancelingPercentage : this.info.value.cancelingPercentage,
      }
      ).subscribe(
        (res) => { 
          this.dataHome = res;
        this._snackBar.open('Vacation home successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      })
  }

  updateAdditional(){
    this._homeService.updateAdditionalInfo(this.homeId,
      {
        utilities: this.services,
        rooms: this.rooms,
      }
    ).subscribe(
      (res) => { 
        this.dataHome = res;
        this._snackBar.open('Vacation home successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      }
    )
  }

  updateAddress(){
    this._homeService.updateAddress(this.homeId, {
      street: this.location.value.street,
      city: this.location.value.city,
      country: this.location.value.country,
      zipCode: this.location.value.zipCode,
    }).subscribe(
      (res) => { 
        this.dataHome = res;
        this._snackBar.open('Vacation home successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      }
    )
  }

  updateRules(){
    this._homeService.updateCodeOfConduct(this.homeId, this.rules).subscribe(
      (res) => { 
        this.dataHome = res;
        this._snackBar.open('Vacation home successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      }
    )
  }

  updateImg(){
    this._homeService.getById(this.homeId).subscribe(
      res => {
        this.dataHome = res;
        this.closeDialog();
      })
  }

  formEnd(){
    // snack??
    this._homeService.getById(this.homeId).subscribe(
      res => {
        this.dataHome = res;
        this.closeDialog();
      })
  }

  closeDialog(){
    this.dialogRef.close({data: {
      editMode : this.editMode,
      boat : this.dataHome,
    }});
  }

  addRoom(){
    if(this.newRoom.value != '' && this.newRoom.value != null){
      console.log(this.newRoom.value);
      this.rooms.push({id: 0 ,numberOfBeds: this.newRoom.value});
      this.newRoom.reset();

    }
  
  }


}

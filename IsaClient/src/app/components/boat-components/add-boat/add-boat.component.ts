import { HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IAddress } from 'src/app/interfaces/address';
import { FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { Boat } from 'src/app/interfaces/boat';
import { Rule } from 'src/app/interfaces/rule';
import { BoatService } from 'src/app/services/boat-service/boat.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-boat',
  templateUrl: './add-boat.component.html',
  styleUrls: ['./add-boat.component.css']
})
export class AddBoatComponent implements OnInit {

  existingEquipment! : FishingEquipment[]
  existingServices! : Utility[]
  existingRules! : Rule[]
  existingNavigation! : string[]

  equipment : FishingEquipment[] = []
  services : Utility[] = []
  rules : Rule[] = []
  navigation: string[] = []
  newABoat : Boat = {} as Boat
  boat : Boat = {} as Boat
  dataBoat : Boat = {} as Boat
  typeSelect : string = '' as string;

  showImgUpload : boolean = false
  requestUrl! : string

  editMode : boolean = false
  boatId! : string

  equi = new FormControl()
  servi = new FormControl()
  rul = new FormControl()
  nav = new FormControl()
  ty= new FormControl()

  headers = new HttpHeaders()
    .set('Access-Control-Allow-Origin', '*')
    .set('Access-Control-Allow-Headers', '*')

  info = this._formBuilder.group({
    name: ['', Validators.required],
    //type: ['', Validators.required],
    length: ['', Validators.required],
    enginePower: ['', Validators.required],
    engineCount: ['', Validators.required],
    maxSpeed: ['', Validators.required],
    pricePerDay: ['', Validators.required],
    description: ['', Validators.required],
    information: ['', Validators.required],
    guestsLimit: ['', Validators.required],
    cancelingPercentage: ['', Validators.required],

  })

  additional = this._formBuilder.group({
    equipmentCtrl: ['', Validators.required],
    serviceName : ['', Validators.required],
    servicePrice : ['', Validators.required],
    navType : ['', Validators.required],
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
              private _boatService : BoatService, 
              public dialogRef: MatDialogRef<AddBoatComponent>,
              private _snackBar : MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public data: any) {

                this.newABoat.address = {} as IAddress

                if(data.editMode)
                {
                  this.boatId = data.boatId;
                  this.editMode = true;
                  this.requestUrl =  environment.apiURL + '/boats/image-upload/' + this.boatId;
                  this._boatService.getById(this.boatId).subscribe(
                    res => {
                      console.log(res)
                      this.boat = res;
                      console.log(this.boat)
                      this.info.setValue({
                        name : this.boat.name,
                        length : res.length,
                        enginePower : res.enginePower,
                        engineCount : res.engineCount,
                        maxSpeed : res.maxSpeed,
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
                      this.equipment = res.fishingEquipment;
                      this.services = res.utilities;
                      this.navigation = res.navigationTypes;
                      this.typeSelect = res.type;
                      
                    }
                   )
                   
                }
                console.log(this.boat)
               }

  ngOnInit(): void {
    this._boatService.getFishingEquipment().subscribe(
      data => {
        this.existingEquipment = data;
      }
    );
    
    this._boatService.getUtilities().subscribe(
      data => {
        this.existingServices = data;
      }
    )

    this._boatService.getCodeOfConduct().subscribe(
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

  createBoat() : any {
    this.createObject();
    this._boatService.addBoat(this.newABoat).subscribe(
      data => {
        this.dataBoat = data;
        this.showImgUpload = true;
        this.requestUrl =  environment.apiURL + '/boats/image-upload/' + data.id;
      }
    );
  }

  createObject() : void {

    this.newABoat.name = this.info.value.name;
    this.newABoat.type = this.typeSelect;
    this.newABoat.length = this.info.value.length;
    this.newABoat.enginePower = this.info.value.enginePower;
    this.newABoat.engineCount = this.info.value.engineCount;
    this.newABoat.maxSpeed = this.info.value.maxSpeed;

    this.newABoat.address.street = this.location.value.street,
    this.newABoat.address.city =  this.location.value.city;
    this.newABoat.address.country = this.location.value.country;
    this.newABoat.address.zipCode = this.location.value.zipCode;
    this.newABoat.description = this.info.value.description;
    this.newABoat.pricePerDay = this.info.value.pricePerDay;
    this.newABoat.information = this.info.value.information;
    this.newABoat.cancelingPercentage = this.info.value.cancellationPercentage;
    this.newABoat.guestLimit = this.info.value.guestsLimit;
    this.newABoat.codeOfConduct = this.rules;
    this.newABoat.ownerEmail = this._storageService.getEmail();
    this.newABoat.fishingEquipment = this.equipment;
    this.newABoat.utilities = this.services;
    this.newABoat.navigationTypes = this.navigation;

  }

  updateInfo(){
    this._boatService.updateInfo(this.boatId,   
      {
        name : this.info.value.name,
        type : this.typeSelect,
        length :this.info.value.length,
        enginePower : this.info.value.enginePower,
        engineCount : this.info.value.engineCount,
        maxSpeed : this.info.value.maxSpeed,
        pricePerDay : this.info.value.pricePerDay,
        description : this.info.value.description,
        information : this.info.value.information,
        guestLimit : this.info.value.guestsLimit,
        cancelingPercentage : this.info.value.cancelingPercentage,
      }
      ).subscribe(
        (res) => { 
          this.dataBoat = res;
        this._snackBar.open('Boat successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      })
  }

  updateAdditional(){
    this._boatService.updateAdditionalInfo(this.boatId,
      {
        fishingEquipment: this.equipment,
        utilities: this.services,
        navigationTypes: this.navigation
      }
    ).subscribe(
      (res) => { 
        this.dataBoat = res;
        this._snackBar.open('Boat successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      }
    )
  }

  updateAddress(){
    this._boatService.updateAddress(this.boatId, {
      street: this.location.value.street,
      city: this.location.value.city,
      country: this.location.value.country,
      zipCode: this.location.value.zipCode,
    }).subscribe(
      (res) => { 
        this.dataBoat = res;
        this._snackBar.open('Boat successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      }
    )
  }

  updateRules(){
    this._boatService.updateCodeOfConduct(this.boatId, this.rules).subscribe(
      (res) => { 
        this.dataBoat = res;
        this._snackBar.open('Boat successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      }
    )
  }

  updateImg(){
    this._boatService.getById(this.boatId).subscribe(
      res => {
        this.dataBoat = res;
        this.closeDialog();
      })
  }

  formEnd(){
    // snack??
    this.closeDialog();
  }

  closeDialog(){
    console.log(this.dataBoat)
    this.dialogRef.close({data: {
      editMode : this.editMode,
      boat : this.dataBoat,
    }});
  }


}

import { HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import {  Adventure, FishingEquipment, Utility } from 'src/app/interfaces/adventure';
import { Rule } from 'src/app/interfaces/rule';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-adventure',
  templateUrl: './add-adventure.component.html',
  styleUrls: ['./add-adventure.component.css']
})
export class AddAdventureComponent implements OnInit {

  existingEquipment! : FishingEquipment[]
  existingServices! : Utility[]
  existingRules! : Rule[]

  equipment : FishingEquipment[] = []
  services : Utility[] = []
  rules : Rule[] = []
  newAdventure : Adventure = {} as Adventure
  adventure : Adventure = {} as Adventure
  dataAdventure : Adventure = {} as Adventure

  showImgUpload : boolean = false
  requestUrl! : string

  editMode : boolean = false
  adventureId! : string


  equi = new FormControl()
  servi = new FormControl()
  rul = new FormControl()

  headers = new HttpHeaders()
    .set('Access-Control-Allow-Origin', '*')
    .set('Access-Control-Allow-Headers', '*')

  info = this._formBuilder.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
    price: ['', Validators.required],
    cancellationPercentage: ['', Validators.required],
    maxPersons: ['', Validators.required],

  })

  additional = this._formBuilder.group({
    equipmentCtrl: ['', Validators.required],
    serviceName : ['', Validators.required],
    servicePrice : ['', Validators.required],
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
              private _adventureService : AdventureService, 
              public dialogRef: MatDialogRef<AddAdventureComponent>,
              private _snackBar : MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public data: any) {

                if(data.editMode)
                {
                  this.adventureId = data.adventureId;
                  this.editMode = true;
                  this.requestUrl =  environment.apiURL + '/adventures/image-upload/' + this.adventureId;
                  this._adventureService.getById(this.adventureId).subscribe(
                    res => {
                      this.adventure = res;
    
                      this.info.setValue({
                        title : res.title,
                        description : res.description,
                        price : res.pricePerDay,
                        cancellationPercentage : res.cancelingPercentage,
                        maxPersons : res.maxNumberOfParticipants,
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
                    }
                   )
                }
               
              
               }

  ngOnInit(): void {
    this._adventureService.getFishingEquipment().subscribe(
      data => {
        this.existingEquipment = data;
      }
    );
    
    this._adventureService.getUtilities().subscribe(
      data => {
        this.existingServices = data;
      }
    )

    this._adventureService.getCodeOfConduct().subscribe(
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

  createAdventure() : any {
    this.createObject();
    this._adventureService.addAdventure(this.newAdventure).subscribe(
      data => {
        this.dataAdventure = data;
        this.showImgUpload = true;
        this.requestUrl =  environment.apiURL + '/adventures/image-upload/' + data.id;
      }
    );
  }

  createObject() : void {

    this.newAdventure.title = this.info.value.title;
    this.newAdventure.address = {
      id : 0,
      street : this.location.value.street,
      city : this.location.value.city,
      country : this.location.value.country,
      zipCode : this.location.value.zipCode,
    };
    this.newAdventure.description = this.info.value.description;
    this.newAdventure.pricePerDay = this.info.value.price;
    this.newAdventure.cancelingPercentage = this.info.value.cancellationPercentage;
    this.newAdventure.maxNumberOfParticipants = this.info.value.maxPersons;
    this.newAdventure.codeOfConduct = this.rules;
    this.newAdventure.instructorEmail = this._storageService.getEmail();
    this.newAdventure.fishingEquipment = this.equipment;
    this.newAdventure.utilities = this.services;

  }

  updateInfo(){
    this._adventureService.updateInfo(this.adventureId,   
      {
        title: this.info.value.title,
        description: this.info.value.description,
        price: this.info.value.price,
        cancelingPercentage: this.info.value.cancellationPercentage,
        maxParticipants: this.info.value.maxPersons
      }
      ).subscribe(
        (res) => { 
          this.dataAdventure = res;
        this._snackBar.open('Adventure successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      })
  }

  updateAdditional(){
    this._adventureService.updateAdditionalInfo(this.adventureId,
      {
        fishingEquipment: this.equipment,
        utilities: this.services
      }
    ).subscribe(
      (res) => { 
        this.dataAdventure = res;
        this._snackBar.open('Adventure successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      }
    )
  }

  updateAddress(){
    this._adventureService.updateAddress(this.adventureId, {
      street: this.location.value.street,
      city: this.location.value.city,
      country: this.location.value.country,
      zipCode: this.location.value.zipCode,
    }).subscribe(
      (res) => { 
        this.dataAdventure = res;
        this._snackBar.open('Adventure successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      this.closeDialog();
      }
    )
  }

  updateRules(){
    this._adventureService.updateCodeOfConduct(this.adventureId, this.rules).subscribe(
      (res) => { 
        this.dataAdventure = res;
        this._snackBar.open('Adventure successfully updated.', '',
        { duration: 3000, panelClass: ['snack-bar'] }
      );
      }
    )
  }

  updateImg(){
    this._adventureService.getById(this.adventureId).subscribe(
      res => {
        this.dataAdventure = res;
        this.closeDialog();

      })
  }

  formEnd(){
    // snack??
    this.closeDialog();
  }

  closeDialog(){
    this.dialogRef.close({data: {
      editMode : this.editMode,
      adventure : this.dataAdventure
    }});
  }

}


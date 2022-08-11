import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
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

  showImgUpload : boolean = false

  requestUrl! : string

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
              private _adventureService : AdventureService) { }

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

  createAdventure() : any {
    this.createObject();
    console.log(this.newAdventure);
    this._adventureService.addAdventure(this.newAdventure).subscribe(
      data => {
        console.log(data);
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
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-adventure',
  templateUrl: './add-adventure.component.html',
  styleUrls: ['./add-adventure.component.css']
})
export class AddAdventureComponent implements OnInit {


  equipment : any[] = []
  services : any[] = [{ 
    name : 'Usluga',
    price : '23'
  }]

  info = this._formBuilder.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
    price: ['', Validators.required],
    cancellationPercentage: ['', Validators.required],
    maxPersons: ['', Validators.required],

  });
  additional = this._formBuilder.group({
    equipment: ['', Validators.required],
    service : ['', Validators.required],
    servicePrice : ['', Validators.required],
  });


  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  addEquipment(){
    this.equipment.push(this.additional.value.equipment)
  }

  addService(){
    this.services.push(this.additional.value.service)
  }
}

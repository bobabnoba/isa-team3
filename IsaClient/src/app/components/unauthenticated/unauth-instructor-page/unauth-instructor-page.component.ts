import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Adventure } from 'src/app/interfaces/adventure';
import { IInstructorInfo } from 'src/app/interfaces/instructor-info';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { InstructorService } from 'src/app/services/instructor-service/instructor.service';

@Component({
  selector: 'app-unauth-instructor-page',
  templateUrl: './unauth-instructor-page.component.html',
  styleUrls: ['./unauth-instructor-page.component.css']
})
export class UnauthInstructorPageComponent implements OnInit {
  item!: IInstructorInfo;
  adventures: Adventure[] = []
  id!: string;
  constructor(private service: InstructorService,
    private _router: Router,
    private _adventureService: AdventureService,) {
    this.id = this._router.url.substring(24) ?? '';
    console.log(this.id);
    this.getDetails();
    this.getAdventures();
  }
  getAdventures(){
    this._adventureService.getAllByInstructorId(this.id).subscribe(
      data => {
        this.adventures = data;
      }
      
    )
  }
  getDetails() {
    const instructorObserver = {
      next: (data: any) => {
        this.item = data;
        console.log(data);

      },
      error: (err: HttpErrorResponse) => {
        this._router.navigate(['/404']);
      },
    }
    this.service.getInstructorInfo(this.id).subscribe(instructorObserver);
  }

  ngOnInit(): void {
  }
}

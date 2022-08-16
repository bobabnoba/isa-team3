import { Component, Input, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Adventure } from 'src/app/interfaces/adventure';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-past-experiences',
  templateUrl: './past-experiences.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./past-experiences.component.css']
})
export class PastExperiencesComponent implements OnInit {

  @Input()
  adventure! : Adventure;
  baseUrl = environment.apiURL

  constructor() { }

  ngOnInit(): void {
    }

}

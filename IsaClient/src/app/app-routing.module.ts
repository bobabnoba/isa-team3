import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { MainPageComponent } from './components/main-page/main-page.component';


const routes: Routes = [
  {
    path: '',
    component: MainPageComponent 
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    MaterialModule,
    HttpClientModule
  ],
  exports: [RouterModule,MaterialModule]
})
export class AppRoutingModule { }

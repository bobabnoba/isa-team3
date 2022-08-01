import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { MainPageComponent } from './components/main-page/main-page.component';
import { FormsModule } from "@angular/forms"
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { OwnerRegisterComponent } from './components/owner-register/owner-register.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page.component';
import { InstructorDashboardComponent } from './components/instructor/instructor-dashboard/instructor-dashboard.component';
import { AdminProfileComponent } from './components/admin/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin/admin-registration-requests/admin-registration-requests.component';

const routes: Routes = [
  {
    path: '',
    component: UnauthenticatedPageComponent,
  },
  {
    path:'home',
    children: [
      {
        path: 'page',
        component: HomePageComponent,
      },

    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'ownerRegistration', component: OwnerRegisterComponent },
  {
    path : 'instructor/dashboard',
    component:  InstructorDashboardComponent,
  },
  {
    path : 'admin/profile',
    component:  AdminProfileComponent,
  },
  {
    path : 'admin/registration-requests',
    component:  AdminRegistrationRequestsComponent,
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [RouterModule, MaterialModule, FormsModule],
})
export class AppRoutingModule { }

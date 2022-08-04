import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { MainPageComponent } from './components/main-page/main-page.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { OwnerRegisterComponent } from './components/owner-register/owner-register.component';
import { InstructorDashboardComponent } from './components/instructor/instructor-dashboard/instructor-dashboard.component';
import { AdminProfileComponent } from './components/admin/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin/admin-registration-requests/admin-registration-requests.component';
import { AdventureProfileComponent } from './components/adventure/adventure-profile/adventure-profile.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: MainPageComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registration',
    component: RegisterComponent,
  },

  {
    path: 'home',
    component: HomePageComponent,
  },

  {
    path: 'ownerRegistration',
    component: OwnerRegisterComponent,
  },
  {
    path : 'instructor/dashboard',
    component:  InstructorDashboardComponent,
  },
  {
    path : 'admin/dashboard',
    component: AdminDashboardComponent
  },
  {
    path : 'admin/profile',
    component:  AdminProfileComponent,
  },
  {
    path : 'admin/registration-requests',
    component:  AdminRegistrationRequestsComponent,
  },
  {
    path : 'adventure',
    component: AdventureProfileComponent
  },

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
export class AppRoutingModule {}

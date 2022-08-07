import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register-components/register/register.component';
import { OwnerRegisterComponent } from './components/register-components/owner-register/owner-register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { InstructorDashboardComponent } from './components/instructor-components/instructor-dashboard/instructor-dashboard.component';
import { AdminProfileComponent } from './components/admin-components/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin-components/admin-registration-requests/admin-registration-requests.component';
import { AdventureProfileComponent } from './components/adventure-components/adventure-profile/adventure-profile.component';
import { AdminDashboardComponent } from './components/admin-components/admin-dashboard/admin-dashboard.component';
import { AdminDeleteRequestsComponent } from './components/admin-components/admin-delete-requests/admin-delete-requests.component';
import { ClientDashboardComponent } from './components/client/client-dashboard/client-dashboard.component';
import { ClientProfileComponent } from './components/client/client-profile/client-profile.component';
import { VacationHomePageComponent } from './components/vacation-home-components/vacation-home-page/vacation-home-page.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page.component';

const routes: Routes = [
  {
    path: '',
    component: UnauthenticatedPageComponent,
  },
  {
    path: 'client/dashboard',
    component: ClientDashboardComponent
  },
  {
    path: 'client/profile',
    component: ClientProfileComponent
  },
  {
    path: 'vacation/home/:id',
    component: VacationHomePageComponent
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
    path: 'ownerRegistration',
    component: OwnerRegisterComponent,
  },
  {
    path: 'instructor/dashboard',
    component: InstructorDashboardComponent,
  },
  {
    path: 'admin/dashboard',
    component: AdminDashboardComponent
  },
  {
    path: 'admin/profile',
    component: AdminProfileComponent,
  },
  {
    path: 'admin/registration-requests',
    component: AdminRegistrationRequestsComponent,
  },
  {
    path: 'admin/account-deletion-requests',
    component: AdminDeleteRequestsComponent
  },
  {
    path: 'adventure',
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
export class AppRoutingModule { }

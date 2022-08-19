import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-components/admin-dashboard/admin-dashboard.component';
import { AdminDeleteRequestsComponent } from './components/admin-components/admin-delete-requests/admin-delete-requests.component';
import { AdminProfileComponent } from './components/admin-components/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin-components/admin-registration-requests/admin-registration-requests.component';
import { AdminUsersComponent } from './components/admin-components/admin-users/admin-users.component';
import { AddAdventureComponent } from './components/adventure-components/add-adventure/add-adventure.component';
import { AdventureProfileComponent } from './components/adventure-components/adventure-profile/adventure-profile.component';
import { AdventureReservationsComponent } from './components/client-components/adventure-reservations/adventure-reservations.component';
import { BoatReservationsComponent } from './components/client-components/boat-reservations/boat-reservations.component';
import { ClientBoatsComponent } from './components/client-components/client-boats/client-boats.component';
import { ClientBrowseComponent } from './components/client-components/client-browse/client-browse.component';
import { ClientHomesComponent } from './components/client-components/client-homes/client-homes.component';
import { ClientInstructorsComponent } from './components/client-components/client-instructors/client-instructors.component';
import { ClientProfileComponent } from './components/client-components/client-profile/client-profile.component';
import { ClientReservationsHistoryComponent } from './components/client-components/client-reservations-history/client-reservations-history.component';
import { ClientReservationsComponent } from './components/client-components/client-reservations/client-reservations.component';
import { HomeReservationsComponent } from './components/client-components/home-reservations/home-reservations.component';
import { InstructorDashboardComponent } from './components/instructor-components/instructor-dashboard/instructor-dashboard.component';
import { InstructorProfileComponent } from './components/instructor-components/instructor-profile/instructor-profile.component';
import { InstructorServiceListComponent } from './components/instructor-components/instructor-service-list/instructor-service-list.component';
import { InstructorServiceComponent } from './components/instructor-components/instructor-service/instructor-service.component';
import { LoginComponent } from './components/login/login.component';
import { OwnerRegisterComponent } from './components/register-components/owner-register/owner-register.component';
import { RegisterComponent } from './components/register-components/register/register.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page.component';
import { VacationHomePageComponent } from './components/vacation-home-components/vacation-home-page/vacation-home-page.component';
import { MaterialModule } from './material/material.module';

const routes: Routes = [
  {
    path: '',
    component: UnauthenticatedPageComponent
  },
  {
    path: 'client/profile',
    component: ClientProfileComponent
  },
  {
    path: 'client/browse',
    component: ClientBrowseComponent
  },
  {
    path: 'client/homes',
    component: ClientHomesComponent
  },
  {
    path: 'client/boats',
    component: ClientBoatsComponent
  },
  {
    path: 'client/instructors',
    component: ClientInstructorsComponent
  },
  {
    path: 'client/homes/reservation',
    component: HomeReservationsComponent
  },
  {
    path: 'client/boats/reservation',
    component: BoatReservationsComponent
  },
  {
    path: 'client/adventures/reservation',
    component: AdventureReservationsComponent
  },
  {
    path: 'client/reservations',
    component: ClientReservationsComponent
  },
  {
    path: 'client/reservations/history',
    component: ClientReservationsHistoryComponent
  },
  {
    path: 'home/:id',
    component: VacationHomePageComponent
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },

  {
    path: 'ownerRegistration',
    component: OwnerRegisterComponent,
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
    path : 'admin/users',
    component: AdminUsersComponent
  },
  {
    path: 'adventure',
    component: AdventureProfileComponent
  },
  {
    path: 'add-adventure',
    component : AddAdventureComponent
  },
  {
    path: 'instructor/dashboard',
    component: InstructorDashboardComponent,
  },
  {
    path: 'instructor/profile',
    component: InstructorProfileComponent
  },
  {
    path : 'instructor/services',
    component: InstructorServiceListComponent
  },
  {
    path : 'instructor/adventure/:id',
    component : InstructorServiceComponent
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

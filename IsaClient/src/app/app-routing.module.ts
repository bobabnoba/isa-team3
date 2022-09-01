import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AdminAdventuresComponent } from './components/admin-components/admin-adventures/admin-adventures.component';
import { AdminBoatsComponent } from './components/admin-components/admin-boats/admin-boats.component';
import { AdminDashboardComponent } from './components/admin-components/admin-dashboard/admin-dashboard.component';
import { AdminDeleteRequestsComponent } from './components/admin-components/admin-delete-requests/admin-delete-requests.component';
import { AdminHomesComponent } from './components/admin-components/admin-homes/admin-homes.component';
import { AdminProfileComponent } from './components/admin-components/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin-components/admin-registration-requests/admin-registration-requests.component';
import { AdminUsersComponent } from './components/admin-components/admin-users/admin-users.component';
import { ReservationReportsComponent } from './components/admin-components/reservation-reports/reservation-reports.component';
import { AddAdventureComponent } from './components/adventure-components/add-adventure/add-adventure.component';
import { AdventureProfileComponent } from './components/adventure-components/adventure-profile/adventure-profile.component';
import { BoatAvailabilityComponent } from './components/boat-components/boat-availability/boat-availability.component';
import { BoatOwnerBoatProfileComponent } from './components/boat-owner-components/boat-owner-boat-profile/boat-owner-boat-profile.component';
import { BoatOwnerBoatsComponent } from './components/boat-owner-components/boat-owner-boats/boat-owner-boats.component';
import { BoatOwnerDashboardComponent } from './components/boat-owner-components/boat-owner-dashboard/boat-owner-dashboard.component';
import { BoatOwnerProfileComponent } from './components/boat-owner-components/boat-owner-profile/boat-owner-profile.component';
import { BoatReservationsCurrentComponent } from './components/boat-owner-components/boat-reservations-current/boat-reservations-current.component';
import { BoatReservationsFutureComponent } from './components/boat-owner-components/boat-reservations-future/boat-reservations-future.component';
import { BoatReservationsHistoryComponent } from './components/boat-owner-components/boat-reservations-history/boat-reservations-history.component';
import { AdventureReservationsComponent } from './components/client-components/adventure-reservations/adventure-reservations.component';
import { BoatReservationsComponent } from './components/client-components/boat-reservations/boat-reservations.component';
import { BoatSubsComponent } from './components/client-components/boat-subs/boat-subs.component';
import { ClientBoatsComponent } from './components/client-components/client-boats/client-boats.component';
import { ClientBrowseComponent } from './components/client-components/client-browse/client-browse.component';
import { ClientHomesComponent } from './components/client-components/client-homes/client-homes.component';
import { ClientInstructorsComponent } from './components/client-components/client-instructors/client-instructors.component';
import { ClientProfileComponent } from './components/client-components/client-profile/client-profile.component';
import { ClientReservationsComponent } from './components/client-components/client-reservations/client-reservations.component';
import { HomeReservationsComponent } from './components/client-components/home-reservations/home-reservations.component';
import { HomeSubsComponent } from './components/client-components/home-subs/home-subs.component';
import { InstructorSubsComponent } from './components/client-components/instructor-subs/instructor-subs.component';
import { ReservationHistoryAdventuresComponent } from './components/client-components/reservation-history-adventures/reservation-history-adventures.component';
import { ReservationHistoryBoatsComponent } from './components/client-components/reservation-history-boats/reservation-history-boats.component';
import { ReservationHistoryHomesComponent } from './components/client-components/reservation-history-homes/reservation-history-homes.component';
import { HomeOwnerDashboardComponent } from './components/home-owner-components/home-owner-dashboard/home-owner-dashboard.component';
import { HomeOwnerProfileComponent } from './components/home-owner-components/home-owner-profile/home-owner-profile.component';
import { InstructorDashboardComponent } from './components/instructor-components/instructor-dashboard/instructor-dashboard.component';
import { InstructorProfileComponent } from './components/instructor-components/instructor-profile/instructor-profile.component';
import { InstructorServiceListComponent } from './components/instructor-components/instructor-service-list/instructor-service-list.component';
import { InstructorServiceComponent } from './components/instructor-components/instructor-service/instructor-service.component';
import { ReservationsHistoryComponent } from './components/instructor-components/reservations-history/reservations-history.component';
import { ReservationsUpcomingComponent } from './components/instructor-components/reservations-upcoming/reservations-upcoming.component';
import { LoginComponent } from './components/login/login.component';
import { OwnerRegisterComponent } from './components/register-components/owner-register/owner-register.component';
import { RegisterComponent } from './components/register-components/register/register.component';
import { BoatPageComponent } from './components/rentals/boat-page/boat-page.component';
import { InstructorPageComponent } from './components/rentals/instructor-page/instructor-page.component';
import { VacationHomePageComponent } from './components/rentals/vacation-home-page/vacation-home-page.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page.component';
import { UnauthBoatPageComponent } from './components/unauthenticated/unauth-boat-page/unauth-boat-page.component';
import { UnauthHomePageComponent } from './components/unauthenticated/unauth-home-page/unauth-home-page.component';
import { UnauthInstructorPageComponent } from './components/unauthenticated/unauth-instructor-page/unauth-instructor-page.component';
import { MaterialModule } from './material/material.module';
import { InstructorAvailabilityComponent } from './components/instructor-components/instructor-availability/instructor-availability.component';
import { AdminReviewsComponent } from './components/admin-components/admin-reviews/admin-reviews.component';
import { HomeOwnerHomesComponent } from './components/home-owner-components/home-owner-homes/home-owner-homes.component';
import { HomeProfileComponent } from './components/home-components/home-profile/home-profile.component';
import { HomeOwnerHomeProfileComponent } from './components/home-owner-components/home-owner-home-profile/home-owner-home-profile.component';
import { HomeAvailabilityComponent } from './components/home-components/home-availability/home-availability.component';
import { HomeReservationsCurrentComponent } from './components/home-owner-components/home-reservations-current/home-reservations-current.component';
import { HomeReservationsFutureComponent } from './components/home-owner-components/home-reservations-future/home-reservations-future.component';
import { HomeReservationsHistoryComponent } from './components/home-owner-components/home-reservations-history/home-reservations-history.component';


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
    path: 'client/homes/history',
    component: ReservationHistoryHomesComponent
  },
  {
    path: 'client/adventures/history',
    component: ReservationHistoryAdventuresComponent
  },
  {
    path: 'client/boats/history',
    component: ReservationHistoryBoatsComponent
  },
  {
    path: 'home/page/:id',
    component: VacationHomePageComponent
  },
  {
    path: 'boat/page/:id',
    component: BoatPageComponent
  },
  {
    path: 'instructor/page/:id',
    component: InstructorPageComponent
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
    path: 'admin/users',
    component: AdminUsersComponent
  },
  {
    path: 'admin/reservation-reports',
    component: ReservationReportsComponent
  },
  {
    path: 'admin/reviews',
    component: AdminReviewsComponent
  },
  {
    path: 'admin/adventures',
    component: AdminAdventuresComponent
  },
  {
    path: 'admin/boats',
    component: AdminBoatsComponent
  },
  {
    path: 'admin/homes',
    component: AdminHomesComponent
  },
  {
    path: 'adventure',
    component: AdventureProfileComponent
  },
  {
    path: 'add-adventure',
    component: AddAdventureComponent
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
    path: 'instructor/services',
    component: InstructorServiceListComponent
  },
  {
    path: 'instructor/adventure/:id',
    component: InstructorServiceComponent
  },
  {
    path: 'instructor/availability',
    component: InstructorAvailabilityComponent
  },
  {
    path: 'home-owner/dashboard',
    component: HomeOwnerDashboardComponent,
  },
  {
    path: 'home-owner/profile',
    component: HomeOwnerProfileComponent
  },
  {
    path: 'boat-owner/dashboard',
    component: BoatOwnerDashboardComponent,
  },
  {
    path: 'boat-owner/profile',
    component: BoatOwnerProfileComponent
  },
  {
    path: 'boat-owner/boats',
    component: BoatOwnerBoatsComponent
  },
  {
    path: 'instructor/reservations/upcoming',
    component: ReservationsUpcomingComponent
  },
  {
    path: 'instructor/reservations/history',
    component: ReservationsHistoryComponent
  },
  {
    path: 'unauth/boat/page/:id',
    component: UnauthBoatPageComponent
  },
  {
    path: 'unauth/home/page/:id',
    component: UnauthHomePageComponent
  },
  {
    path: 'unauth/instructor/page/:id',
    component: UnauthInstructorPageComponent
  },

  {
    path: 'boat-owner/boat/:id',
    component: BoatOwnerBoatProfileComponent
  },
  {
    path: 'boat-owner/boat/calendar/:id',
    component: BoatAvailabilityComponent
  },
  {
    path: 'boat-owner/reservations/past',
    component: BoatReservationsHistoryComponent
  },

  {
    path: 'boat-owner/reservations/future',
    component: BoatReservationsFutureComponent
  },
  {
    path: 'boat-owner/reservations/current',
    component: BoatReservationsCurrentComponent
  },
  {
    path: 'client/subscribed/boats',
    component: BoatSubsComponent
  },
  {
    path: 'client/subscribed/homes',
    component: HomeSubsComponent
  },
  {
    path: 'client/subscribed/instructors',
    component: InstructorSubsComponent
  },
  {
    path: 'home-owner/homes',
    component: HomeOwnerHomesComponent
  },
  {
    path: 'home-owner/home/:id',
    component: HomeOwnerHomeProfileComponent
  },
  {
    path: 'home-owner/home/calendar/:id',
    component: HomeAvailabilityComponent
  },
  {
    path: 'home-owner/reservations/past',
    component: HomeReservationsHistoryComponent
  },

  {
    path: 'home-owner/reservations/future',
    component: HomeReservationsFutureComponent
  },
  {
    path: 'home-owner/reservations/current',
    component: HomeReservationsCurrentComponent
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

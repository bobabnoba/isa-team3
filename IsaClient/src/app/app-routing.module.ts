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
import { BoatOwnerAvailabilityComponent } from './components/boat-owner-components/boat-owner-availability/boat-owner-availability.component';
import { AdminComplaintsComponent } from './components/admin-components/admin-complaints/admin-complaints.component';
import { ReservationChartsComponent } from './components/boat-owner-components/reservation-charts/reservation-charts.component';
import { HomeReservationChartComponent } from './components/home-owner-components/home-reservation-chart/home-reservation-chart.component';
import { AdventureReservationChartsComponent } from './components/instructor-components/adventure-reservation-charts/adventure-reservation-charts.component';
import { ErrorNotFoundComponent } from './pages/error-not-found/error-not-found.component';
import { ErrorForbiddenComponent } from './pages/error-forbidden/error-forbidden.component';
import { AuthGuardAdmin } from './AuthGuard/AuthGuardAdmin';
import { AuthGuardInstructor } from './AuthGuard/AuthGuardInstructor';
import { AuthGuardHomeOwner } from './AuthGuard/AuthGuardHomeOwner';
import { AuthGuardBoatOwner } from './AuthGuard/AuthGuardBoatOwner';
import { AuthGuardClient } from './AuthGuard/AuthGuardClient';


const routes: Routes = [
  {
    path: '',
    component: UnauthenticatedPageComponent
  },
  {
    path: 'client/profile',
    component: ClientProfileComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/browse',
    component: ClientBrowseComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/homes',
    component: ClientHomesComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/boats',
    component: ClientBoatsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/instructors',
    component: ClientInstructorsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/homes/reservation',
    component: HomeReservationsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/boats/reservation',
    component: BoatReservationsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/adventures/reservation',
    component: AdventureReservationsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/reservations',
    component: ClientReservationsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/homes/history',
    component: ReservationHistoryHomesComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/adventures/history',
    component: ReservationHistoryAdventuresComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/boats/history',
    component: ReservationHistoryBoatsComponent,
    canActivate: [AuthGuardClient] 
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
    component: AdminDashboardComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/profile',
    component: AdminProfileComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/registration-requests',
    component: AdminRegistrationRequestsComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/account-deletion-requests',
    component: AdminDeleteRequestsComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/users',
    component: AdminUsersComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/reservation-reports',
    component: ReservationReportsComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/reviews',
    component: AdminReviewsComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path:'admin/complaints',
    component: AdminComplaintsComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/adventures',
    component: AdminAdventuresComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/boats',
    component: AdminBoatsComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'admin/homes',
    component: AdminHomesComponent,
    canActivate: [AuthGuardAdmin] 
  },
  {
    path: 'adventure',
    component: AdventureProfileComponent,
    canActivate: [AuthGuardInstructor, AuthGuardAdmin, AuthGuardClient] 
  },
  {
    path: 'add-adventure',
    component: AddAdventureComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
    path: 'instructor/dashboard',
    component: InstructorDashboardComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
    path: 'instructor/profile',
    component: InstructorProfileComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
    path: 'instructor/services',
    component: InstructorServiceListComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
    path: 'instructor/adventure/:id',
    component: InstructorServiceComponent,
    canActivate: [AuthGuardInstructor, AuthGuardAdmin, AuthGuardClient] 
  },
  {
    path: 'instructor/availability',
    component: InstructorAvailabilityComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
    path: 'home-owner/dashboard',
    component: HomeOwnerDashboardComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'home-owner/profile',
    component: HomeOwnerProfileComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'boat-owner/dashboard',
    component: BoatOwnerDashboardComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'boat-owner/profile',
    component: BoatOwnerProfileComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'boat-owner/boats',
    component: BoatOwnerBoatsComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'boat-owner/availability',
    component: BoatOwnerAvailabilityComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'instructor/reservations/upcoming',
    component: ReservationsUpcomingComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
    path: 'instructor/reservations/history',
    component: ReservationsHistoryComponent,
    canActivate: [AuthGuardInstructor] 
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
    component: BoatOwnerBoatProfileComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'boat-owner/boat/calendar/:id',
    component: BoatAvailabilityComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'boat-owner/reservations/past',
    component: BoatReservationsHistoryComponent,
    canActivate: [AuthGuardBoatOwner] 
  },

  {
    path: 'boat-owner/reservations/future',
    component: BoatReservationsFutureComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'boat-owner/reservations/current',
    component: BoatReservationsCurrentComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'client/subscribed/boats',
    component: BoatSubsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/subscribed/homes',
    component: HomeSubsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'client/subscribed/instructors',
    component: InstructorSubsComponent,
    canActivate: [AuthGuardClient] 
  },
  {
    path: 'home-owner/homes',
    component: HomeOwnerHomesComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'home-owner/home/:id',
    component: HomeOwnerHomeProfileComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'home-owner/home/calendar/:id',
    component: HomeAvailabilityComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'home-owner/reservations/past',
    component: HomeReservationsHistoryComponent,
    canActivate: [AuthGuardHomeOwner] 
  },

  {
    path: 'home-owner/reservations/future',
    component: HomeReservationsFutureComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'home-owner/reservations/current',
    component: HomeReservationsCurrentComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'boat-owner/charts',
    component: ReservationChartsComponent,
    canActivate: [AuthGuardBoatOwner] 
  },
  {
    path: 'home-owner/charts',
    component: HomeReservationChartComponent,
    canActivate: [AuthGuardHomeOwner] 
  },
  {
    path: 'instructor/charts',
    component: AdventureReservationChartsComponent,
    canActivate: [AuthGuardInstructor] 
  },
  {
     path: '404',
     component: ErrorNotFoundComponent 
  },
  {
    path: '403',
    component: ErrorForbiddenComponent 
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

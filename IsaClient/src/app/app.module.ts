import { CommonModule, DatePipe } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSliderModule } from '@angular/material/slider';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAdminComponent } from './components/admin-components/add-admin/add-admin.component';
import { AdminDashboardComponent } from './components/admin-components/admin-dashboard/admin-dashboard.component';
import { AdminDeleteRequestsComponent } from './components/admin-components/admin-delete-requests/admin-delete-requests.component';
import { AdminEarningsComponent } from './components/admin-components/admin-earnings/admin-earnings.component';
import { AdminProfileComponent } from './components/admin-components/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin-components/admin-registration-requests/admin-registration-requests.component';
import { AdminResponseComponent } from './components/admin-components/admin-response/admin-response.component';
import { AdminSidebarComponent } from './components/admin-components/admin-sidebar/admin-sidebar.component';
import { AdminTopbarComponent } from './components/admin-components/admin-topbar/admin-topbar.component';
import { AdminUsersComponent } from './components/admin-components/admin-users/admin-users.component';
import { LoyaltyProgramComponent } from './components/admin-components/loyalty-program/loyalty-program.component';
import { ReportResponseComponent } from './components/admin-components/report-response/report-response.component';
import { ReservationReportsComponent } from './components/admin-components/reservation-reports/reservation-reports.component';
import { AddAdventureComponent } from './components/adventure-components/add-adventure/add-adventure.component';
import { AdventureAddtionalInfoComponent } from './components/adventure-components/adventure-addtional-info/adventure-addtional-info.component';
import { AdventureInfoComponent } from './components/adventure-components/adventure-info/adventure-info.component';
import { AdventureInstructorComponent } from './components/adventure-components/adventure-instructor/adventure-instructor.component';
import { AdventureLocationComponent } from './components/adventure-components/adventure-location/adventure-location.component';
import { AdventurePreviewComponent } from './components/adventure-components/adventure-preview/adventure-preview.component';
import { AdventureProfileComponent } from './components/adventure-components/adventure-profile/adventure-profile.component';
import { AdventureServicesComponent } from './components/adventure-components/adventure-services/adventure-services.component';
import { AdventureSpecialOfferComponent } from './components/adventure-components/adventure-special-offer/adventure-special-offer.component';
import { PastExperiencesComponent } from './components/adventure-components/past-experiences/past-experiences.component';
import { AdvertiserEarningsComponent } from './components/advertiser-earnings/advertiser-earnings.component';
import { AddBoatComponent } from './components/boat-components/add-boat/add-boat.component';
import { BoatAdditionalInfoComponent } from './components/boat-components/boat-additional-info/boat-additional-info.component';
import { BoatInfoComponent } from './components/boat-components/boat-info/boat-info.component';
import { BoatLocationComponent } from './components/boat-components/boat-location/boat-location.component';
import { BoatOwnerInfoComponent } from './components/boat-components/boat-owner-info/boat-owner-info.component';
import { BoatPreviewComponent } from './components/boat-components/boat-preview/boat-preview.component';
import { BoatProfileComponent } from './components/boat-components/boat-profile/boat-profile.component';
import { BoatServicesComponent } from './components/boat-components/boat-services/boat-services.component';
import { BoatOwnerAccDeletionExplanationComponent } from './components/boat-owner-components/boat-owner-acc-deletion-explanation/boat-owner-acc-deletion-explanation.component';
import { BoatOwnerBoatProfileComponent } from './components/boat-owner-components/boat-owner-boat-profile/boat-owner-boat-profile.component';
import { BoatOwnerBoatsComponent } from './components/boat-owner-components/boat-owner-boats/boat-owner-boats.component';
import { BoatOwnerDashboardComponent } from './components/boat-owner-components/boat-owner-dashboard/boat-owner-dashboard.component';
import { BoatOwnerProfileComponent } from './components/boat-owner-components/boat-owner-profile/boat-owner-profile.component';
import { BoatOwnerSidebarComponent } from './components/boat-owner-components/boat-owner-sidebar/boat-owner-sidebar.component';
import { BoatOwnerTopbarComponent } from './components/boat-owner-components/boat-owner-topbar/boat-owner-topbar.component';
import { BrowseCardComponent } from './components/browse-card/browse-card.component';
import { BrowseInstructorCardComponent } from './components/browse-instructor-card/browse-instructor-card.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { AdventureReservationsComponent } from './components/client-components/adventure-reservations/adventure-reservations.component';
import { BoatReservationsComponent } from './components/client-components/boat-reservations/boat-reservations.component';
import { BrowseCardReservationComponent } from './components/client-components/browse-card-reservation/browse-card-reservation.component';
import { ClientBoatsComponent } from './components/client-components/client-boats/client-boats.component';
import { ClientBrowseComponent } from './components/client-components/client-browse/client-browse.component';
import { ClientHomesComponent } from './components/client-components/client-homes/client-homes.component';
import { ClientInstructorsComponent } from './components/client-components/client-instructors/client-instructors.component';
import { ClientProfileComponent } from './components/client-components/client-profile/client-profile.component';
import { ClientReservationsComponent } from './components/client-components/client-reservations/client-reservations.component';
import { ClientSearchCardReservationComponent } from './components/client-components/client-search-card-reservation/client-search-card-reservation.component';
import { ClientSearchCardComponent } from './components/client-components/client-search-card/client-search-card.component';
import { ClientSidebarComponent } from './components/client-components/client-sidebar/client-sidebar.component';
import { ClientTopBarComponent } from './components/client-components/client-top-bar/client-top-bar.component';
import { HomeReservationsComponent } from './components/client-components/home-reservations/home-reservations.component';
import { ReservationHistoryAdventuresComponent } from './components/client-components/reservation-history-adventures/reservation-history-adventures.component';
import { ReservationHistoryBoatsComponent } from './components/client-components/reservation-history-boats/reservation-history-boats.component';
import { ReservationHistoryHomesComponent } from './components/client-components/reservation-history-homes/reservation-history-homes.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeOwnerAccDeletionExplanationComponent } from './components/home-owner-components/home-owner-acc-deletion-explanation/home-owner-acc-deletion-explanation.component';
import { HomeOwnerDashboardComponent } from './components/home-owner-components/home-owner-dashboard/home-owner-dashboard.component';
import { HomeOwnerProfileComponent } from './components/home-owner-components/home-owner-profile/home-owner-profile.component';
import { HomeOwnerSidebarComponent } from './components/home-owner-components/home-owner-sidebar/home-owner-sidebar.component';
import { AccDeletionExplanationComponent } from './components/instructor-components/acc-deletion-explanation/acc-deletion-explanation.component';
import { AddSpecialOfferComponent } from './components/instructor-components/add-special-offer/add-special-offer.component';
import { ClientInfoComponent } from './components/instructor-components/client-info/client-info.component';
import { InstructorCalendarComponent } from './components/instructor-components/instructor-calendar/instructor-calendar.component';
import { InstructorCreateReservationComponent } from './components/instructor-components/instructor-create-reservation/instructor-create-reservation.component';
import { InstructorDashboardComponent } from './components/instructor-components/instructor-dashboard/instructor-dashboard.component';
import { InstructorProfileComponent } from './components/instructor-components/instructor-profile/instructor-profile.component';
import { InstructorReportComponent } from './components/instructor-components/instructor-report/instructor-report.component';
import { InstructorServiceListComponent } from './components/instructor-components/instructor-service-list/instructor-service-list.component';
import { InstructorServiceComponent } from './components/instructor-components/instructor-service/instructor-service.component';
import { InstructorSidebarComponent } from './components/instructor-components/instructor-sidebar/instructor-sidebar.component';
import { InstructorTopbarComponent } from './components/instructor-components/instructor-topbar/instructor-topbar.component';
import { ReservationInfoComponent } from './components/instructor-components/reservation-info/reservation-info.component';
import { ReservationPreviewComponent } from './components/instructor-components/reservation-preview/reservation-preview.component';
import { ReservationsHistoryComponent } from './components/instructor-components/reservations-history/reservations-history.component';
import { ReservationsUpcomingComponent } from './components/instructor-components/reservations-upcoming/reservations-upcoming.component';
import { LoginComponent } from './components/login/login.component';
import { OwnerRegisterComponent } from './components/register-components/owner-register/owner-register.component';
import { RegisterComponent } from './components/register-components/register/register.component';
import { BoatPageComponent } from './components/rentals/boat-page/boat-page.component';
import { InstructorPageComponent } from './components/rentals/instructor-page/instructor-page.component';
import { ReservationViewComponent } from './components/reservation-view/reservation-view.component';
import { SearchCardComponent } from './components/search-card/search-card.component';
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import { UnauthenticatedHeaderComponent } from './components/unauthenticated-header/unauthenticated-header.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page.component';
import { UnauthBoatPageComponent } from './components/unauthenticated/unauth-boat-page/unauth-boat-page.component';
import { UnauthHomePageComponent } from './components/unauthenticated/unauth-home-page/unauth-home-page.component';
import { UnauthInstructorPageComponent } from './components/unauthenticated/unauth-instructor-page/unauth-instructor-page.component';
import { JwtInterceptor } from './JwtInterceptor/jwt-interceptor';
import { MaterialModule } from './material/material.module';
import { AdventureSearchPipe } from './pipes/adventure-search/adventure-search.pipe';
import { BoatSearchPipe } from './pipes/boat-search/boat-search.pipe';
import { TruncatePipe } from './pipes/truncate-pipe';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AdminAdventuresComponent } from './components/admin-components/admin-adventures/admin-adventures.component';
import { AdminBoatsComponent } from './components/admin-components/admin-boats/admin-boats.component';
import { AdminHomesComponent } from './components/admin-components/admin-homes/admin-homes.component';
import { AvailabilityCalendarComponent } from './components/boat-components/availability-calendar/availability-calendar.component';
import { BoatAvailabilityComponent } from './components/boat-components/boat-availability/boat-availability.component';
import { BoatSpecialOfferComponent } from './components/boat-components/boat-special-offer/boat-special-offer.component';
import { AddBoatSpecialOfferComponent } from './components/boat-owner-components/add-boat-special-offer/add-boat-special-offer.component';
import { AvailabilityCalendarBoatOwnerComponent } from './components/boat-owner-components/availability-calendar-boat-owner/availability-calendar-boat-owner.component';
import { BoatOwnerCreateReservationComponent } from './components/boat-owner-components/boat-owner-create-reservation/boat-owner-create-reservation.component';
import { BoatOwnerReportComponent } from './components/boat-owner-components/boat-owner-report/boat-owner-report.component';
import { InstructorAvailabilityComponent } from './components/instructor-components/instructor-availability/instructor-availability.component';
import { BoatReservationInfoComponent } from './components/boat-owner-components/boat-reservation-info/boat-reservation-info.component';
import { BoatReservationPreviewComponent } from './components/boat-owner-components/boat-reservation-preview/boat-reservation-preview.component';
import { BoatReservationsCurrentComponent } from './components/boat-owner-components/boat-reservations-current/boat-reservations-current.component';
import { BoatReservationsFutureComponent } from './components/boat-owner-components/boat-reservations-future/boat-reservations-future.component';
import { BoatReservationsHistoryComponent } from './components/boat-owner-components/boat-reservations-history/boat-reservations-history.component';
import { BoatSubsComponent } from './components/client-components/boat-subs/boat-subs.component';
import { ComplaintComponent } from './components/client-components/complaint/complaint.component';
import { HomeOwnerHomesComponent } from './components/home-owner-components/home-owner-homes/home-owner-homes.component';
import { HomeSearchPipe } from './pipes/home-search/home-search.pipe';
import { HomeOwnerTopbarComponent } from './components/home-owner-components/home-owner-topbar/home-owner-topbar.component';
import { HomePreviewComponent } from './components/home-components/home-preview/home-preview.component';
import { HomeProfileComponent } from './components/home-components/home-profile/home-profile.component';
import { HomeInfoComponent } from './components/home-components/home-info/home-info.component';
import { HomeAdditionalInfoComponent } from './components/home-components/home-additional-info/home-additional-info.component';
import { HomeLocationComponent } from './components/home-components/home-location/home-location.component';
import { HomeOwnerInfoComponent } from './components/home-components/home-owner-info/home-owner-info.component';
import { HomeServicesComponent } from './components/home-components/home-services/home-services.component';
import { HomeOwnerHomeProfileComponent } from './components/home-owner-components/home-owner-home-profile/home-owner-home-profile.component';
import { AddHomeComponent } from './components/home-components/add-home/add-home.component';
import { HomeAvailabilityCalendarComponent } from './components/home-components/home-availability-calendar/home-availability-calendar.component';
import { HomeAvailabilityComponent } from './components/home-components/home-availability/home-availability.component';
import { AddHomeSpecialOfferComponent } from './components/home-owner-components/add-home-special-offer/add-home-special-offer.component';
import { HomeReservationInfoComponent } from './components/home-owner-components/home-reservation-info/home-reservation-info.component';
import { HomeReservationsCurrentComponent } from './components/home-owner-components/home-reservations-current/home-reservations-current.component';
import { HomeReservationsFutureComponent } from './components/home-owner-components/home-reservations-future/home-reservations-future.component';
import { HomeReservationsHistoryComponent } from './components/home-owner-components/home-reservations-history/home-reservations-history.component';
import { HomeOwnerCreateReservationComponent } from './components/home-owner-components/home-owner-create-reservation/home-owner-create-reservation.component';
import { HomeSubsComponent } from './components/client-components/home-subs/home-subs.component';
import { InstructorSubsComponent } from './components/client-components/instructor-subs/instructor-subs.component';
import { ReviewComponent } from './components/client-components/review/review.component';
import { SubCardComponent } from './components/client-components/sub-card/sub-card.component';
import { AdminReviewsComponent } from './components/admin-components/admin-reviews/admin-reviews.component';
import { ImageListItemComponent } from './components/common/image-list-item/image-list-item.component';
import { ImageListComponent } from './components/common/image-list/image-list.component';
import { VacationHomePageComponent } from './components/rentals/vacation-home-page/vacation-home-page.component';
import { BoatOwnerAvailabilityComponent } from './components/boat-owner-components/boat-owner-availability/boat-owner-availability.component';
import { AdminComplaintsComponent } from './components/admin-components/admin-complaints/admin-complaints.component';
import { MapComponent } from './components/map/map.component';
import { ImagesComponent } from './components/boat-components/images/images.component';
import { ReservationChartsComponent } from './components/boat-owner-components/reservation-charts/reservation-charts.component';
import { HomeReservationChartComponent } from './components/home-owner-components/home-reservation-chart/home-reservation-chart.component';
import { AdventureReservationChartsComponent } from './components/instructor-components/adventure-reservation-charts/adventure-reservation-charts.component';
import { ErrorNotFoundComponent } from './pages/error-not-found/error-not-found.component';
import { ErrorForbiddenComponent } from './pages/error-forbidden/error-forbidden.component';
import { RegisterAsComponent } from './components/register-components/register-as/register-as.component';

@NgModule({
  declarations: [
    AppComponent,
    TruncatePipe,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    OwnerRegisterComponent,
    InstructorDashboardComponent,
    InstructorSidebarComponent,
    InstructorTopbarComponent,
    AdminSidebarComponent,
    AdminTopbarComponent,
    AdminProfileComponent,
    AdminRegistrationRequestsComponent,
    AdminResponseComponent,
    AdventureProfileComponent,
    AdventureInfoComponent,
    AdventureAddtionalInfoComponent,
    AdventureLocationComponent,
    AdventureServicesComponent,
    AdventureInstructorComponent,
    PastExperiencesComponent,
    AdminDashboardComponent,
    AdminDeleteRequestsComponent,
    ClientSidebarComponent,
    ClientProfileComponent,
    UnauthenticatedPageComponent,
    UnauthenticatedHeaderComponent,
    VacationHomePageComponent,
    ReservationViewComponent,
    InstructorCalendarComponent,
    BrowseCardComponent,
    SearchCardComponent,
    ClientReservationsComponent,
    ClientBrowseComponent,
    ClientHomesComponent,
    ClientInstructorsComponent,
    ClientBoatsComponent,
    ClientSearchCardComponent,
    BrowseInstructorCardComponent,
    AddAdventureComponent,
    InstructorServiceComponent,
    AdventurePreviewComponent,
    InstructorServiceListComponent,
    InstructorProfileComponent,
    AccDeletionExplanationComponent,
    HomeReservationsComponent,
    BoatReservationsComponent,
    AdventureReservationsComponent,
    AddSpecialOfferComponent,
    AdventureSpecialOfferComponent,
    AdventureSearchPipe,
    ClientSearchCardReservationComponent,
    BrowseCardReservationComponent,
    ClientTopBarComponent,
    AdminUsersComponent,
    LoyaltyProgramComponent,
    BoatOwnerSidebarComponent,
    BoatOwnerDashboardComponent,
    BoatOwnerProfileComponent,
    HomeOwnerSidebarComponent,
    HomeOwnerProfileComponent,
    HomeOwnerDashboardComponent,
    HomeOwnerAccDeletionExplanationComponent,
    BoatOwnerAccDeletionExplanationComponent,
    BoatOwnerBoatsComponent,
    BoatPreviewComponent,
    BoatOwnerTopbarComponent,
    AddBoatComponent,
    BoatSearchPipe,
    BoatProfileComponent,
    BoatOwnerBoatProfileComponent,
    BoatInfoComponent,
    BoatAdditionalInfoComponent,
    BoatLocationComponent,
    BoatServicesComponent,
    BoatOwnerInfoComponent,
    StarRatingComponent,
    ReservationPreviewComponent,
    ReservationsUpcomingComponent,
    ReservationsHistoryComponent,
    ClientInfoComponent,
    ReservationInfoComponent,
    ReservationReportsComponent,
    ReportResponseComponent,
    InstructorReportComponent,
    InstructorCreateReservationComponent,
    BoatPageComponent,
    InstructorPageComponent,
    UnauthBoatPageComponent,
    UnauthHomePageComponent,
    UnauthInstructorPageComponent,
    ReservationHistoryBoatsComponent,
    ReservationHistoryHomesComponent,
    ReservationHistoryAdventuresComponent,
    AddAdminComponent,
    ChangePasswordComponent,
    AdminEarningsComponent,
    AdvertiserEarningsComponent,
    BoatAvailabilityComponent,
    AvailabilityCalendarComponent,
    ImageListComponent,
    ImageListItemComponent,
    AddBoatSpecialOfferComponent,
    BoatSpecialOfferComponent,
    AvailabilityCalendarBoatOwnerComponent,
    ReviewComponent,
    AdminAdventuresComponent,
    AdminBoatsComponent,
    AdminHomesComponent,
    InstructorAvailabilityComponent,
    BoatReservationInfoComponent,
    BoatReservationPreviewComponent,
    BoatReservationsHistoryComponent,
    BoatReservationsFutureComponent,
    BoatReservationsCurrentComponent,
    BoatOwnerCreateReservationComponent,
    BoatOwnerReportComponent,
    HomeOwnerHomesComponent,
    HomeSearchPipe,
    HomeOwnerTopbarComponent,
    HomePreviewComponent,
    HomeProfileComponent,
    HomeInfoComponent,
    HomeAdditionalInfoComponent,
    HomeLocationComponent,
    HomeOwnerInfoComponent,
    HomeServicesComponent,
    HomeOwnerHomeProfileComponent,
    AddHomeComponent,
    HomeAvailabilityCalendarComponent,
    HomeAvailabilityComponent,
    AddHomeSpecialOfferComponent,
    HomeReservationInfoComponent,
    HomeReservationsCurrentComponent,
    HomeReservationsFutureComponent,
    HomeReservationsHistoryComponent,
    HomeOwnerCreateReservationComponent,
    HomeSubsComponent,
    InstructorSubsComponent,
    BoatSubsComponent,
    SubCardComponent,
    AdminReviewsComponent,
    ComplaintComponent,
    BoatOwnerAvailabilityComponent,
    AdminComplaintsComponent,
    MapComponent,
    ImagesComponent,
    ReservationChartsComponent,
    HomeReservationChartComponent,
    AdventureReservationChartsComponent,
    ErrorNotFoundComponent,
    ErrorForbiddenComponent,
    RegisterAsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSnackBarModule,
    MatSelectModule,
    MatSidenavModule,
    MatCardModule,
    MatCheckboxModule,
    MatSliderModule,
    MatPaginatorModule,
    MatTableModule,
    MatListModule,
    MatButtonModule,
    MatTabsModule,
    MatDialogModule,
    NgbModule,
    NgbModalModule,
    MatDatepickerModule,
    MatNativeDateModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    MatStepperModule,
    MatFileUploadModule,
    MatIconModule,
    MatFormFieldModule,
  ],
  exports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSnackBarModule,
    MatSelectModule,
    MatSidenavModule,
    MatCardModule,
    MatCheckboxModule,
    MatSliderModule,
    MatListModule,
    MatButtonModule,
    MatDialogModule,
    CalendarModule,
    MatFormFieldModule,
  ],
  providers: [HttpClientModule, DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }

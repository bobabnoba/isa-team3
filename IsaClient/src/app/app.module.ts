import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { VacationHomePageComponent } from './components/vacation-home-components/vacation-home-page/vacation-home-page.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register-components/register/register.component';
import { MaterialModule } from './material/material.module';
import { CommonModule, DatePipe } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { OwnerRegisterComponent } from './components/register-components/owner-register/owner-register.component';
import { MatSelectModule } from '@angular/material/select';
import { InstructorDashboardComponent } from './components/instructor-components/instructor-dashboard/instructor-dashboard.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { InstructorSidebarComponent } from './components/instructor-components/instructor-sidebar/instructor-sidebar.component';
import { InstructorTopbarComponent } from './components/instructor-components/instructor-topbar/instructor-topbar.component';
import { AdminSidebarComponent } from './components/admin-components/admin-sidebar/admin-sidebar.component';
import { AdminTopbarComponent } from './components/admin-components/admin-topbar/admin-topbar.component';
import { AdminProfileComponent } from './components/admin-components/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin-components/admin-registration-requests/admin-registration-requests.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdminResponseComponent } from './components/admin-components/admin-response/admin-response.component';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { AdventureProfileComponent } from './components/adventure-components/adventure-profile/adventure-profile.component';
import { AdventureInfoComponent } from './components/adventure-components/adventure-info/adventure-info.component';
import { AdventureAddtionalInfoComponent } from './components/adventure-components/adventure-addtional-info/adventure-addtional-info.component';
import { AdventureLocationComponent } from './components/adventure-components/adventure-location/adventure-location.component';
import { AdventureServicesComponent } from './components/adventure-components/adventure-services/adventure-services.component';
import { AdventureInstructorComponent } from './components/adventure-components/adventure-instructor/adventure-instructor.component';
import { PastExperiencesComponent } from './components/adventure-components/past-experiences/past-experiences.component';
import { AdminDashboardComponent } from './components/admin-components/admin-dashboard/admin-dashboard.component';
import { AdminDeleteRequestsComponent } from './components/admin-components/admin-delete-requests/admin-delete-requests.component';
import { ClientSidebarComponent } from './components/client-components/client-sidebar/client-sidebar.component';
import { ClientProfileComponent } from './components/client-components/client-profile/client-profile.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page.component';
import { UnauthenticatedHeaderComponent } from './components/unauthenticated-header/unauthenticated-header.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSliderModule } from '@angular/material/slider';
import { MatListModule } from '@angular/material/list';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReservationViewComponent } from './components/reservation-view/reservation-view.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { InstructorCalendarComponent } from './components/instructor-components/instructor-calendar/instructor-calendar.component';
import { BrowseCardComponent } from './components/browse-card/browse-card.component';
import { TruncatePipe } from './pipes/truncate-pipe';
import { SearchCardComponent } from './components/search-card/search-card.component';
import { ClientReservationsComponent } from './components/client-components/client-reservations/client-reservations.component';
import { ClientReservationsHistoryComponent } from './components/client-components/client-reservations-history/client-reservations-history.component';
import { ClientBrowseComponent } from './components/client-components/client-browse/client-browse.component';
import { ClientHomesComponent } from './components/client-components/client-homes/client-homes.component';
import { ClientInstructorsComponent } from './components/client-components/client-instructors/client-instructors.component';
import { ClientBoatsComponent } from './components/client-components/client-boats/client-boats.component';
import { ClientSearchCardComponent } from './components/client-components/client-search-card/client-search-card.component';
import { BrowseInstructorCardComponent } from './components/browse-instructor-card/browse-instructor-card.component';
import { AddAdventureComponent } from './components/adventure-components/add-adventure/add-adventure.component';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { InstructorServiceComponent } from './components/instructor-components/instructor-service/instructor-service.component';
import { JwtInterceptor } from './JwtInterceptor/jwt-interceptor';
import { AdventurePreviewComponent } from './components/adventure-components/adventure-preview/adventure-preview.component';
import { InstructorServiceListComponent } from './components/instructor-components/instructor-service-list/instructor-service-list.component';
import { InstructorProfileComponent } from './components/instructor-components/instructor-profile/instructor-profile.component';
import { AccDeletionExplanationComponent } from './components/instructor-components/acc-deletion-explanation/acc-deletion-explanation.component';
import { HomeReservationsComponent } from './components/client-components/home-reservations/home-reservations.component';
import { BoatReservationsComponent } from './components/client-components/boat-reservations/boat-reservations.component';
import { AdventureReservationsComponent } from './components/client-components/adventure-reservations/adventure-reservations.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { AddSpecialOfferComponent } from './components/instructor-components/add-special-offer/add-special-offer.component';
import { MatNativeDateModule } from '@angular/material/core';
import { AdventureSpecialOfferComponent } from './components/adventure-components/adventure-special-offer/adventure-special-offer.component';
import { AdventureSearchPipe } from './pipes/adventure-search/adventure-search.pipe';
import { ClientSearchCardReservationComponent } from './components/client-components/client-search-card-reservation/client-search-card-reservation.component';
import { BrowseCardReservationComponent } from './components/client-components/browse-card-reservation/browse-card-reservation.component';
import { ClientTopBarComponent } from './components/client-components/client-top-bar/client-top-bar.component';
import { AdminUsersComponent } from './components/admin-components/admin-users/admin-users.component';
import { LoyaltyProgramComponent } from './components/admin-components/loyalty-program/loyalty-program.component';
import { BoatOwnerSidebarComponent } from './components/boat-owner-components/boat-owner-sidebar/boat-owner-sidebar.component';
import { BoatOwnerDashboardComponent } from './components/boat-owner-components/boat-owner-dashboard/boat-owner-dashboard.component';
import { BoatOwnerProfileComponent } from './components/boat-owner-components/boat-owner-profile/boat-owner-profile.component';
import { HomeOwnerSidebarComponent } from './components/home-owner-components/home-owner-sidebar/home-owner-sidebar.component';
import { HomeOwnerProfileComponent } from './components/home-owner-components/home-owner-profile/home-owner-profile.component';
import { HomeOwnerDashboardComponent } from './components/home-owner-components/home-owner-dashboard/home-owner-dashboard.component';
import { HomeOwnerAccDeletionExplanationComponent } from './components/home-owner-components/home-owner-acc-deletion-explanation/home-owner-acc-deletion-explanation.component';
import { BoatOwnerAccDeletionExplanationComponent } from './components/boat-owner-components/boat-owner-acc-deletion-explanation/boat-owner-acc-deletion-explanation.component';
import { BoatOwnerBoatsComponent } from './components/boat-owner-components/boat-owner-boats/boat-owner-boats.component';
import { BoatPreviewComponent } from './components/boat-components/boat-preview/boat-preview.component';
import { BoatOwnerTopbarComponent } from './components/boat-owner-components/boat-owner-topbar/boat-owner-topbar.component';
import { AddBoatComponent } from './components/boat-components/add-boat/add-boat.component';
import { BoatSearchPipe } from './pipes/boat-search/boat-search.pipe';
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import { MatIconModule } from '@angular/material/icon';
import { ReservationPreviewComponent } from './components/instructor-components/reservation-preview/reservation-preview.component';
import { ReservationsUpcomingComponent } from './components/instructor-components/reservations-upcoming/reservations-upcoming.component';
import { ReservationsHistoryComponent } from './components/instructor-components/reservations-history/reservations-history.component';
import { ClientInfoComponent } from './components/instructor-components/client-info/client-info.component';
import { ReservationInfoComponent } from './components/instructor-components/reservation-info/reservation-info.component';
import { ReservationReportsComponent } from './components/admin-components/reservation-reports/reservation-reports.component';
import { ReportResponseComponent } from './components/admin-components/report-response/report-response.component';

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
    ClientReservationsHistoryComponent,
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
    StarRatingComponent,
    ReservationPreviewComponent,
    ReservationsUpcomingComponent,
    ReservationsHistoryComponent,
    ClientInfoComponent,
    ReservationInfoComponent,
    ReservationReportsComponent,
    ReportResponseComponent,
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
    MatIconModule
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
  ],
  providers: [HttpClientModule, DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }

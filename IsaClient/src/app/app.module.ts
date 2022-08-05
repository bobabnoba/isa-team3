import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { VacationHouseProfileComponent } from './components/vacation-house-profile/vacation-house-profile.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MaterialModule } from './material/material.module';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { OwnerRegisterComponent } from './components/owner-register/owner-register.component';
import { MatSelectModule } from '@angular/material/select';
import { InstructorDashboardComponent } from './components/instructor/instructor-dashboard/instructor-dashboard.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { InstructorSidebarComponent } from './components/instructor/instructor-sidebar/instructor-sidebar.component';
import { InstructorTopbarComponent } from './components/instructor/instructor-topbar/instructor-topbar.component';
import { AdminSidebarComponent } from './components/admin/admin-sidebar/admin-sidebar.component';
import { AdminTopbarComponent } from './components/admin/admin-topbar/admin-topbar.component';
import { AdminProfileComponent } from './components/admin/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin/admin-registration-requests/admin-registration-requests.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { AdminResponseComponent } from './components/admin/admin-response/admin-response.component';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { AdventureProfileComponent } from './components/adventure/adventure-profile/adventure-profile.component';
import { AdventureInfoComponent } from './components/adventure/adventure-info/adventure-info.component';
import { AdventureAddtionalInfoComponent } from './components/adventure/adventure-addtional-info/adventure-addtional-info.component';
import { AdventureLocationComponent } from './components/adventure/adventure-location/adventure-location.component';
import { AdventureServicesComponent } from './components/adventure/adventure-services/adventure-services.component';
import { AdventureInstructorComponent } from './components/adventure/adventure-instructor/adventure-instructor.component';
import { PastExperiencesComponent } from './components/adventure/past-experiences/past-experiences.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AdminDeleteRequestsComponent } from './components/admin/admin-delete-requests/admin-delete-requests.component';
import { ClientSidebarComponent } from './components/client/client-sidebar/client-sidebar.component';
import { ClientProfileComponent } from './components/client/client-profile/client-profile.component';
import { ClientDashboardComponent } from './components/client/client-dashboard/client-dashboard.component';
import { UnauthenticatedPageComponent } from './components/unauthenticated-page/unauthenticated-page/unauthenticated-page.component';
import { UnauthenticatedHeaderComponent } from './components/unauthenticated-header/unauthenticated-header/unauthenticated-header.component';
import { RentalViewComponent } from './components/rental-view/rental-view/rental-view.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSliderModule } from '@angular/material/slider';
import { MatListModule } from '@angular/material/list';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VacationHomePageComponent } from './components/vacation-home-page/vacation-home-page/vacation-home-page.component';
import { ReservationViewComponent } from './components/reservation-view/reservation-view/reservation-view.component';


@NgModule({
  declarations: [
    AppComponent,
    VacationHouseProfileComponent,
    HeaderComponent,
    FooterComponent,
    MainPageComponent,
    HomePageComponent,
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
    ClientDashboardComponent,
    UnauthenticatedPageComponent,
    UnauthenticatedHeaderComponent,
    RentalViewComponent,
    VacationHomePageComponent,
    ReservationViewComponent
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
    NgbModule

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
    MatDialogModule


  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule { }

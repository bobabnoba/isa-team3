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
import {MatSidenavModule} from '@angular/material/sidenav';
import { InstructorSidebarComponent } from './components/instructor/instructor-sidebar/instructor-sidebar.component';
import { InstructorTopbarComponent } from './components/instructor/instructor-topbar/instructor-topbar.component';
import { AdminSidebarComponent } from './components/admin/admin-sidebar/admin-sidebar.component';
import { AdminTopbarComponent } from './components/admin/admin-topbar/admin-topbar.component';
import { AdminProfileComponent } from './components/admin/admin-profile/admin-profile.component';
import { AdminRegistrationRequestsComponent } from './components/admin/admin-registration-requests/admin-registration-requests.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';


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
    MatPaginatorModule,
    MatTableModule,
    MatButtonModule

  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule {}

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
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon'
import { OwnerRegisterComponent } from './components/owner-register/owner-register.component';
import { DeleteAccountFormComponent } from './components/delete-account-form/delete-account-form.component';


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
    DeleteAccountFormComponent,
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
    MatDialogModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule {}

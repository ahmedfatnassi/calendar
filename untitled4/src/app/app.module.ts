import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FullCalendarModule} from '@fullcalendar/angular';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from 'ng-pick-datetime';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ColorPickerModule} from 'ngx-color-picker';
import { LoginComponent } from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import {AuthguardService} from './login/authguard.service';
import {JwtInterceptor} from './login/jwt-interceptor.service';
import {ChatService} from './calendar/chat.service';

const appRoutes: Routes = [
  { path: 'home',      component: CalendarComponent , canActivate: [AuthguardService] },
  { path: 'login' , component:  LoginComponent } ,
{ path: '**',
    redirectTo: '/login' , pathMatch: 'full'} ,

];
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CalendarComponent
  ],
  imports: [
    BrowserModule,
    FullCalendarModule,
    HttpClientModule,
    FormsModule ,
    NgbModalModule  ,
    RouterModule.forRoot(
      appRoutes) ,
    BrowserAnimationsModule ,
    ColorPickerModule ,
    OwlDateTimeModule, // for time imput
    OwlNativeDateTimeModule, // for date imput
  ],
  providers: [DatePipe , { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true } , ChatService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FullCalendarModule} from '@fullcalendar/angular';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModalModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from 'ng-pick-datetime';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ColorPickerModule} from 'ngx-color-picker';
import { LoginComponent } from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import {AuthguardService} from './login/authguard.service';
import {JwtInterceptor} from './login/jwt-interceptor.service';
import {ToastrModule} from 'ngx-toastr';
import {Stomp} from '@stomp/stompjs';
import { KanbanBoardComponent } from './boardlist/kanban-board/kanban-board.component';
import { BoardlistComponent } from './boardlist/boardlist.component';
import {NgDragDropModule} from 'ng-drag-drop';
import { ChatComponent } from './chat/chat.component';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import { ChatlistComponent } from './chatlist/chatlist.component';
import { SettingsComponent } from './settings/settings.component';
import { NavbarComponent } from './navbar/navbar.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { ReceivedRequestComponent } from './boardlist/received-request/received-request.component';
import { CompletedRequestComponent } from './boardlist/completed-request/completed-request.component';
import { RequestFormComponent } from './request-form/request-form.component';
import {QRCodeModule} from 'angularx-qrcode';
import {BrowserQRCodeReader} from '@zxing/library';
import {ZXingScannerModule} from '@zxing/ngx-scanner';
 // @ts-ignore
import {MatAutocompleteModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatRippleModule} from '@angular/material';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {DashboardComponent} from './dashboard/dashboard.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { ChartModule } from 'angular2-chartjs';
import {CORPORATE_THEME, COSMIC_THEME, DARK_THEME, DEFAULT_THEME, NbCardModule, NbThemeModule} from '@nebular/theme';

const appRoutes: Routes = [
  { path: 'board/:id',      component: KanbanBoardComponent , canActivate: [AuthguardService] },
  { path: 'chats',      component: ChatComponent , canActivate: [AuthguardService] },
  { path: 'dashboard',      component: DashboardComponent , canActivate: [AuthguardService] },
  { path: 'received_request',      component: ReceivedRequestComponent , canActivate: [AuthguardService] },
  { path: 'processed_request',      component: CompletedRequestComponent , canActivate: [AuthguardService] },
  { path: 'request_form',      component : RequestFormComponent , canActivate: [AuthguardService] },
  { path: 'boards',      component: BoardlistComponent , canActivate: [AuthguardService] },
  { path: 'settings',      component: SettingsComponent , canActivate: [AuthguardService] },
  { path: 'home',      component: CalendarComponent , canActivate: [AuthguardService] },
  { path: 'login' , component:  LoginComponent } ,
{ path: '**',
    redirectTo: '/login' , pathMatch: 'full'} ,

];
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CalendarComponent,
    DashboardComponent,
    KanbanBoardComponent,
    BoardlistComponent,
    ChatComponent,
    ChatlistComponent,
    SettingsComponent,
    NavbarComponent,
    ReceivedRequestComponent,
    CompletedRequestComponent,
    RequestFormComponent
  ],
  imports: [

    NgxEchartsModule,
    NgxChartsModule,
    ChartModule,
    NbCardModule,
    BrowserModule,
    QRCodeModule,
    BrowserModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    ZXingScannerModule ,
    FullCalendarModule,
    HttpClientModule,
    MatAutocompleteModule,
    ScrollingModule,
    FormsModule,
    NgDragDropModule.forRoot(),
    Ng2SearchPipeModule,
    NgbModule,
    ReactiveFormsModule,
    NgbModalModule,

    RouterModule.forRoot(
      appRoutes),
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      progressAnimation: 'increasing',
      progressBar: true,
      positionClass: 'toast-bottom-left'

    }),
    ColorPickerModule,
    OwlDateTimeModule, // for time imput
    OwlNativeDateTimeModule,
    DragDropModule,
    // for date imput
  ],
  providers: [DatePipe , { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true } ,
    NbThemeModule.forRoot(
    {
      name: 'default',
    },
    [ DEFAULT_THEME, COSMIC_THEME, CORPORATE_THEME, DARK_THEME ],
  ).providers,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

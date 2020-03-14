import {Component, OnInit, ViewChild} from '@angular/core';
import {EventsService} from '../events.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {OptionsInput} from '@fullcalendar/core';
import {FullCalendarComponent} from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGrigPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import resourceTimelinePlugin from '@fullcalendar/resource-timeline';
import resourceTimeGridPlugin from '@fullcalendar/resource-timegrid';
import {NgForm} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  constructor( private eventservice: EventsService , private modal: NgbModal, public datepipe: DatePipe , private router: Router) {}
  get yearMonth(): string {
    const dateObj = new Date();
    return dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
  }
  options: OptionsInput;
  newevent: any ;
  eventsModel = [] ;
  resources1 = [] ;

  eventsModel1 = [{
    title: 'Updaten nooooo',
    start: '2020-02-20',
    end: '2020-02-20' ,
    resourceId: 'a',
    editable: false ,
    color: 'rouge'

  }] ;
  doctors: any = [] ;
  patients: any = [] ;
  agents: any = [] ;
  @ViewChild('fullcalendar', { static: false }) fullcalendar: FullCalendarComponent;
  // tslint:disable-next-line:new-parens
  myDate = new Date;
  resources  = [] ;
  eventcreationStartDate: string  ;
  eventCreationEndDate: string ;
  // tslint:disable-next-line:variable-name
  color: any;
  ngOnInit() {
    this.resources =  [
      {id : '1' ,
        title : ' roomA '} ,
      {id : '2' ,
        title : ' roomB ' }] ;

    this.options = {
      editable: false,
      header: {
        left: 'prev,next today ',
        center: 'title ',
        right: 'dayGridMonth , timeGridWeek , timeGridDay ,listWeek ,resourceTimeline ,resourceTimeGridDay   '
      },
      schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source' ,
      plugins: [dayGridPlugin, interactionPlugin, timeGrigPlugin, listPlugin , resourceTimelinePlugin , resourceTimeGridPlugin ] ,
    };
    this.eventservice.getDoctors().subscribe(data => {
      this.doctors = Object.keys(data).map(i => data[i]);
      console.log(this.doctors)  ;
      // tslint:disable-next-line:forin prefer-for-of
      for (let i = 0; i < data.length; i++) {
        this.addResource(this.doctors[i].name , this.doctors[i].id) ;
      }
      this.resources = this.resources1 ;
    }) ;

    this.eventservice.getPatients().subscribe(data => {
      this.patients = Object.keys(data).map(i => data[i]);
      console.log(this.patients) ;
    }) ;
    this.eventservice.getAgents().subscribe(data => {
      this.agents = Object.keys(data).map(i => data[i]);
      console.log(this.agents) ;
    }) ;

    this.eventservice.getEvents().subscribe(
      (data ) => {
        console.log('here is events ') ;
        console.log(data);
        // tslint:disable-next-line:forin prefer-for-of
        for (let i = 0; i < data.length; i++) {
          console.log(i) ;
          this.addEvent(data[i].title, data[i].startEvent, data[i].endEvent, data[i].idReceiver, data[i].color);
        }
        this.eventsModel = this.eventsModel1 ;
        console.log('iam heeere ') ;
        console.log(this.eventsModel) ;

      });

  }
  eventClick(model) {
    console.log(model);
  }
  newEvent(content) {
    const modalRef = this.modal.open(content);

  }
  eventDragStop(model) {
    console.log(model);
  }

  dateClick(model) {
    console.log(model);
    console.log(this.datepipe.transform(this.myDate, 'yyyy-MM-d'));
  }

  updateEvents() {
    this.eventsModel = [{
      title: 'Updaten Event',
      resourceId: '1',
      start: this.datepipe.transform(this.myDate, 'yyyy-MM-d') ,
      end: this.yearMonth + '-10'
    }];
  }
  addEvent(title1: string, start: Date , end: Date, idReceiver: string , color: string ): void {
    console.log(this.datepipe.transform(this.myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ) ;
    // @ts-ignore
    this.eventsModel1 = this.eventsModel1.concat({
      title: title1,
      resourceId: idReceiver,
      start: this.datepipe.transform(start, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ,
      end: this.datepipe.transform(end, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss')  ,
      editable: false   , // :stop drag and drop
      color
    }) ;

  }
  addResource(title: string, id: string): void {
    console.log(this.datepipe.transform(this.myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ) ;
    this.resources1 = this.resources1.concat({
      id,
      title

    }) ;

  }
  submitCompany(form: NgForm) {
    console.log(form.value);


    // tslint:disable-next-line:max-line-length
    this.eventcreationStartDate = this.datepipe.transform(form.value.date, 'yyyy-MM-dd').toString() + 'T' + this.datepipe.transform(form.value.time[0] , 'HH:mm:ss').toString() ;
    // tslint:disable-next-line:max-line-length
    this.eventCreationEndDate = this.datepipe.transform(form.value.date , 'yyyy-MM-dd').toString() + 'T' + this.datepipe.transform(form.value.time[1], 'HH:mm:ss').toString()  ;
    this.modal.dismissAll() ;
    console.log(this.eventCreationEndDate) ;
    // tslint:disable-next-line:max-line-length
    this.newevent = {
      idSender : form.value.patient.id ,
      idReceiver : form.value.doctor.id,
      title : form.value.title ,
      startEvent  : this.eventcreationStartDate ,
      endEvent :  this.eventCreationEndDate ,
      color : this.color
    };
    this.eventservice.PostEvents(this.newevent);
    this.eventsModel1 = this.eventsModel  ;
    this.addEvent(this.newevent.title ,
      this.newevent.startEvent ,
      this.newevent.endEvent ,
      this.newevent.idReceiver ,
      this.newevent.color) ;
    this.eventsModel = this.eventsModel1 ;
  }
  onChange(newValue) {
    console.log(newValue);
    this.eventservice.getEventsByreceiver(newValue.value)
      .subscribe(data => {
        this.eventsModel1 = [] ;
        console.log('heeeere iam ') ;

        // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < data.length; i++) {
          console.log('salem ');
          console.log(data[i]);
          this.addEvent(data[i].title, data[i].startEvent, data[i].endEvent, newValue.value, data[i].color );

        }
        this.eventsModel = this.eventsModel1 ;


      }) ,
      // tslint:disable-next-line:no-shadowed-variable no-unused-expression
      (error ) => console.log('error', error) ;
  }
  clearEvents() {
    this.eventsModel = [];
  }
  addEvents() {
    this.eventsModel = [{
      title: 'Updaten nooooo1',
      resourceId: 'a',
      start: '2020-02-20',
      end: '2020-02-20' ,


    } , {
      title: 'Updaten nooooo2',
      resourceId: 'a',
      start: '2020-02-20',
      end: '2020-02-20' ,


    }, {
      title: 'Updaten nooooo3',
      resourceId: 'a',
      start: '2020-02-20',
      end: '2020-02-20' ,


    }];
  }
  logout() {
    console.log('logout') ;
    this.eventservice.logout() ;
    this.router.navigate(['/home']);
  }
}

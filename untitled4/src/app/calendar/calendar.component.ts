import {AfterContentInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
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
import {NotificationhandlerService} from './notificationhandler.service';
import {Subject, Subscription} from 'rxjs';
import {Stomp} from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {ToastrModule, ToastrService} from 'ngx-toastr';
@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit , OnDestroy , AfterContentInit {


  private messages: Subject<any>;
  private messageLog: any;
  private connected: Subscription;

  constructor( private eventservice: EventsService ,
               private modal: NgbModal,
               public datepipe: DatePipe ,
               private router: Router ,
               private  notification: NotificationhandlerService ,
               private toast: ToastrService
  ) {}
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
  client: any ;
  eventCreationEndDate: string ;
  // tslint:disable-next-line:variable-name
  color: any;
  connectmsg: any ;
  private stompClient: any;

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
    //this.notification.connect1()
    // notification
    // 1

    //2
    // tslint:disable-next-line:no-shadowed-variable

   /* this.messages
      .subscribe(
      (data) => {
        console.log('received   ' + data ); // or just use it directly
      }, error => {
        console.error(error); // handle errors
      }) ;*/

  //  this.messages.next(JSON.stringify(msg)) ;
  //  const url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
    // 'localhost:8080/greeting/websocket' ;
    //this.client = Stomp.client(url);
    // tslint:disable-next-line:only-arrow-functions
  /*  this.client.connect( {name : this.eventservice.currentUserValue.username} , function(frame) {
      // tslint:disable-next-line:only-arrow-functions
      console.log(' connected ' ) ;
      // tslint:disable-next-line:no-unused-expression
      // tslint:disable-next-line:only-arrow-functions
    }, function(error) {
      console.log('STOMP error ' + error);
    });*/
    /*const url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
    const socket = new WebSocket(url);
    const stompClient = Stomp.over(socket);
    this.stompClient = stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/user/queue/reply', function(message) {
        console.log('Error websocket' + message);

      });

    }) ;*/

    this.stompClient = this.connect1();

      }

  ngAfterContentInit(): void {
    console.log('Method not implemented.');
  }
  eventClick(model) {
    console.log(model);
  //  this.chat.messages.next(this.message);
    //this.sendMsg() ;
  }
   message = {
    author: 'tutorialedge',
    message: 'this is a test message'
  };
//https://stackoverflow.com/questions/54763261/how-to-send-custom-message-to-custom-user-with-spring-websocket
  sendMsg() {
    console.log('new message from client to websocket: ', this.message);
  }
  newEvent(content) {
    const modalRef = this.modal.open(content, );
    console.log('this.connectmsg ' +this.connectmsg)
    // tslint:disable-next-line:only-arrow-functions

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
    console.log('recieved person '+ form.value.doctor.username)
    this.stompClient.send('/user/'+form.value.doctor.username+'/queue/reply', {}, JSON.stringify(this.newevent));
    this.stompClient.send('/queue/broadcast', {}, JSON.stringify(this.newevent));


  }
  connect1():any{
    const url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
    const socket = new WebSocket(url);
    const stompClient = Stomp.over(socket);
    const toast = this.toast;
    let eventsModel =  this.eventsModel ;
    let eventsModel1 =  this.eventsModel ;
    let that = this;
    stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/user/queue/reply', function(message) {
        console.log('working  websocket  message = ' + JSON.parse(message.body).title);
        toast.info('new event has been created ', 'creation of new event ', {
          timeOut: 5000,
        });


        // that.messages.push(JSON.parse(.body).content);
        /*that.eventsModel1 =that.eventsModel;

        that.eventsModel1 = that.eventsModel1.concat({
          title: newEvent.title,
          resourceId: newEvent.resourceId,
          start:  newEvent.start,
          end:  newEvent.end ,
          editable: false   , // :stop drag and drop
          color : newEvent.color
        }) ;
        that.eventsModel =that.eventsModel1;*/
        console.log(eventsModel);
        return stompClient ;
      });
      stompClient.subscribe('/queue/broadcast', function(message) {
        console.log("broadcast "+message) ;
        const newevent = JSON.parse(message.body)
        that.eventsModel1 = that.eventsModel  ;
        that.addEvent(newevent.title ,
          newevent.startEvent ,
          newevent.endEvent ,
          newevent.idReceiver ,
          newevent.color) ;
        that.eventsModel = that.eventsModel1 ;
      });
      return stompClient ;
    }) ;
    console.log("heeeeeeeeere you are ")
    return stompClient;
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
  ngOnDestroy(): void {

  }
}

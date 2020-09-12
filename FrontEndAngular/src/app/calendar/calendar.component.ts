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
import {FormControl, NgForm} from '@angular/forms';
import {Router} from '@angular/router';
import {NotificationhandlerService} from './notificationhandler.service';
import {Observable, Subject, Subscription} from 'rxjs';
import {Stomp} from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {ToastrModule, ToastrService} from 'ngx-toastr';
import {ChatService} from '../chatlist/chat.service';
import {map, startWith} from 'rxjs/operators';
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
               private toast: ToastrService ,
               private chatService: ChatService ) {}
  get yearMonth(): string {
    const dateObj = new Date();
    return dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
  }
  receiverId: any ;
  keyword = 'title';
  options: OptionsInput;
  myControl = new FormControl();
  options1 :any[];
  messageContainers1:any ;
  filteredOptions: Observable<string[]>;
  newevent: any ;
  socket: any ;
  eventsModel = [] ;
  resources1 = [] ;
  stomp :any ;
  receivedmsg : any ;
  stompClient: any ;
  url : any ;
  eventsModel1 = [] ;
  doctors: any = [] ;
  patients: any = [] ;
  agents: any = [] ;
  @ViewChild('fullcalendar', { static: false }) fullcalendar: FullCalendarComponent;
  // tslint:disable-next-line:new-parens
  myDate = new Date;
  resources  = [] ;
  eventcreationStartDate: string  ;
  currentUser:  any ;
  client: any ;
  eventCreationEndDate: string ;
  // tslint:disable-next-line:variable-name
  color: any;
  connectmsg: any ;
  teamchecked: boolean ;
  userchecked: boolean ;
  teamlist : any[]
  receivers: any[];
  selectedEvent: any ;
  selectedEventReceiver: any ;

  ngOnInit() {
    this.teamchecked =true ;
    this.userchecked =false  ;


    this.options = {
      editable: false,
      header: {
        left: 'prev,next today ',
        center: 'title ',
        right: 'dayGridMonth , timeGridWeek , timeGridDay ,listWeek  ,resourceTimeGridDay   '
      },
      schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source' ,
      plugins: [dayGridPlugin, interactionPlugin, timeGrigPlugin, listPlugin  , resourceTimeGridPlugin ] ,
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

    this.eventservice.getAllusers().subscribe(data => {
      this.receivers = Object.keys(data).map(i => data[i]);
     // console.log(this.patients) ;
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
          this.addEvent(data[i].id, data[i].title, data[i].startEvent, data[i].endEvent, data[i].receiverId, data[i].color, data[i].receiver_type);
        }
        this.eventsModel = this.eventsModel1 ;
        console.log('iam heeere ') ;
        console.log(this.eventsModel) ;

      });
    console.log('this.eventservice.currentUserValue')
    console.log(this.eventservice.currentUserValue)
    this.chatService.getuserbyusername(    this.eventservice.currentUserValue.username).subscribe((currentUser: any) => {
      console.log(currentUser);
      this.currentUser = currentUser ;

      this.chatService.getAllTeamsbyEmployeeID(this.currentUser.id).subscribe((teamlist: any)=>{
        console.log('data')
        console.log(teamlist) ;
        this.teamlist =  teamlist


        this.connect1();
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


    });
      }
  displayFn(user: any): string {
    return user && user.title ? user.title : '';
  }

  handleChangeuserofTeam(event) {

    console.log(event.form.value.customRadio)
    if(event.form.value.customRadio == 'users') {
      this.teamchecked =false  ;
      this.userchecked =true  ;
      this.keyword = 'username'

      this.options1 = [];
      this.options1 = this.receivers ;


    } else {
      this.teamchecked =true ;
      this.userchecked =false  ;
      this.options1 = [];
      this.keyword = 'title'

      this.options1 = this.teamlist ;


    }


  }
  ngAfterContentInit(): void {
    console.log('Method not implemented.');
  }
  eventClick(model,content) {
    console.log(model.event._def.publicId);
    for (let i = 0; i < this.eventsModel.length; i++) {

      if (JSON.stringify(this.eventsModel[i].id) === model.event._def.publicId){
        console.log('it  works ')
          this.selectedEvent = this.eventsModel[i] ;
          break;
      }
    }
    if(this.selectedEvent.receiver_type === 'teams') {
      for (let i = 0; i < this.teamlist.length; i++) {
        if(this.teamlist[i].id === this.selectedEvent.receiverId){
          this.selectedEventReceiver = this.teamlist[i] ;
          break ;
        }
      }
    }else {
      for (let i = 0; i < this.receivers.length; i++) {
        if(this.receivers[i].id === this.selectedEvent.receiverId){
          this.selectedEventReceiver = this.receivers[i] ;
          break ;
        }
      }
    }
    console.log('this.selectedEvent')
    console.log(this.selectedEvent)
    console.log('this.selectedEventReceiver')
    console.log(this.selectedEventReceiver)
    this.modal.open(content );
  //  this.chat.messages.next(this.message);
    //this.sendMsg() ;


  }

//https://stackoverflow.com/questions/54763261/how-to-send-custom-message-to-custom-user-with-spring-websocket
  sendMsg() {
  }
  newEvent(content) {
    const modalRef = this.modal.open(content, );
    this.teamchecked = true
    this.options1 = this.teamlist;


    console.log(this.filteredOptions)
    console.log('this.connectmsg ' +this.connectmsg) ;
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
  addEvent(id: any , title1: string, start: Date , end: Date, idreceiver: string , color: string , receivertype: string ): void {
    console.log(this.datepipe.transform(this.myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ) ;
    // @ts-ignore
    this.eventsModel1 = this.eventsModel1.concat({
      id: id,
      title: title1,
      receiver_type : receivertype ,
      receiverId  : idreceiver,
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
      receiver_type : form.value.customRadio,
      receiverId  : this.receiverId ,
      title : form.value.title ,
      startEvent  : this.eventcreationStartDate ,
      endEvent :  this.eventCreationEndDate ,
      color : this.color
    };
    console.log(this.newevent)
    this.eventservice.PostEvents(this.newevent) .subscribe((data:any) => {
      console.log('show event ') ;
      console.log(data) ;
      this.addEvent(data.id, data.title, data.startEvent, data.endEvent, data.receiverId, data.color, data.receiver_type);
     // this.stompClient.send('/user/'+form.value.doctor.username+'/queue/reply', {}, JSON.stringify(this.newevent));
      this.eventsModel = this.eventsModel1 ;
      if (data.receiver_type === 'teams'){
        for (let i = 0; i < this.teamlist.length; i++) {
          if(this.teamlist[i].id === data.receiverId){
           this.sendToteam(this.teamlist[i].title, data )
            break ;
          }
        }
      }else {
        for (let i = 0; i < this.receivers.length; i++) {
          if(this.receivers[i].id === data.receiverId){
            this.sendtouser(this.receivers[i].username, data);
            break ;
          }
        }
      }
    }) ;

   // this.stompClient.send('/queue/broadcast', {}, JSON.stringify(this.newevent));

  }



  connect1():any{
    this.url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
    this.socket = new WebSocket(this.url);
    this.stompClient = Stomp.over(this.socket);
    const that = this ;
    this.stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);




          for (let i = 0; i < that.teamlist.length; i++) {
            that.stompClient.subscribe('/queue/notificationboard/' +that.teamlist[i].title, function(newevent) {
              that.addEvent(
                newevent.id ,
                newevent.title ,
                newevent.startEvent ,
                newevent.endEvent ,
                newevent.receiverId ,
                newevent.color,
                newevent.receiver_type) ;
              that.eventsModel1 =that.eventsModel;
              that.toast.info('new event has been created for  your team ', 'creation of new event ', {
                timeOut: 5000,
              });
            });
          }

        that.stompClient.subscribe('/user/queue/notification', function(newevent) {

          that.addEvent(
            newevent.id ,
            newevent.title ,
            newevent.startEvent ,
            newevent.endEvent ,
            newevent.receiverId ,
            newevent.color,
            newevent.receiver_type) ;

          that.eventsModel1 =that.eventsModel;
          that.toast.info('new event has been created for you ', 'creation of new event ', {
            timeOut: 5000,
          });
          //that.seletedContainer.last_message = message.body ;
          //that.seletedContainer.last_message_Date = that.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss')  ;
          // this.messages.push(this.message) ;




       //  that.messages.push(JSON.parse(.body).content);
        that.eventsModel1 =that.eventsModel;

    }) ;
    });
    console.log("heeeeeeeeere you are ")
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
          this.addEvent(data[i].id,data[i].title, data[i].startEvent, data[i].endEvent, newValue.value, data[i].color ,data[i].receivertype );

        }
        this.eventsModel = this.eventsModel1 ;


      }) ,
      // tslint:disable-next-line:no-shadowed-variable no-unused-expression
      (error ) => console.log('error', error) ;
  }
  clearEvents() {
    this.eventsModel = [];
  }

  sendtouser(username:any , event : any ){
    console.log('username '+ username)
    this.stompClient.send('/user/' + username + '/queue/notification', {}, JSON.stringify(event));

    console.log('sent looks like ');
  }
  sendToteam(teamtitle:any , event : any ){
    this.stompClient.send( '/queue/notificationboard/'+teamtitle, {}, JSON.stringify(event));

  }
  ngOnDestroy(): void {

  }
  selectEvent(item) {
    // do something with selected item
    console.log('item')
    console.log(item)
    this.receiverId = item.id ;
  }

  onChangeSearch(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.

  }

  onFocused(e){
    // do something when input is focused
  }
}

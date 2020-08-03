import {Component, Inject, OnInit} from '@angular/core';
import {ChatService} from '../chatlist/chat.service';
import {EventsService} from '../events.service';
import {FormControl, NgForm} from '@angular/forms';
import {Stomp} from '@stomp/stompjs';
import {DatePipe, DOCUMENT} from '@angular/common';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {SettingsService} from '../settings/settings.service';
import {forkJoin, Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
messages: any ;
  private message: any;
  constructor(private chatService: ChatService,
              private  eventservice: EventsService ,
              @Inject(DOCUMENT) private document: Document ,
              private modal: NgbModal ,
              private settingsService: SettingsService ,
              public datepipe: DatePipe ,) { }
  receivers : any ;
  search :any ;
  indivChats: any ;
  receiver : any ;
  currentuser: any ;
  stomp :any ;
  receivedmsg : any ;
  stompClient: any ;
  url : any ;
  teamlist: any ;
  socket: any ;
  messagescontainer : any ;
  //https://stackblitz.com/angular/lrkvvylgqal?file=src%2Fapp%2Fautocomplete-auto-active-first-option-example.ts (1)
  myControl = new FormControl();
  options: any[] ;
  options1 :any[];
  messageContainers1:any ;messageContainers:any ;
  filteredOptions: Observable<string[]>;
  userchecked : boolean
  teamchecked : boolean
  seletedContainer:any ;
  listtype: string ;
  findtheContainer:boolean ;
  ngOnInit() {
    //console.log(this.currentuser) ;
    //(1)
    this.seletedContainer = null;
    this.teamchecked =true ;
    this.userchecked =false  ;
    this.messagescontainer =  document.getElementById("messagescontainer");
    this.stomp = this.connect1();
    this.chatService.getuserbyusername(    this.eventservice.currentUserValue.username).subscribe(data =>{
      console.log(data);
      this.currentuser =  data;



    }) ;
      this.eventservice.getAllusers().subscribe(data => {
        console.log('doctors ') ;
        console.log(data);
        this.receivers = Object.keys(data).map(i => data[i]);



        this.settingsService.getAllTeams().subscribe((data:any[] )=>{
          this.teamlist = data ;
          this.options1 = [] ;
          for (let i = 0; i < this.teamlist.length; i++) {
            this.options1.push(this.teamlist[i].title)
          }
          this.options = this.teamlist;
          this.filteredOptions = this.myControl.valueChanges.pipe(
            startWith(''),
            map(value => this._filter(value))
          );


        this.chatService.getAllByIdsenderOrAndIdreceiver(this.currentuser.id).subscribe((data :any[]) => {
          console.log('messageContainers1 ')
          console.log(data)
          this.messageContainers1 = data;

          // trying to set the name of the messagecontainer based on the current user
          this.updatemessageContainer();
        }) ;
        }) ;

      });


    this.scrolldown();

  }
seletContainer(container:any){
    this.seletedContainer = container ;
    console.log(this.seletedContainer) ;
    this.chatService.getAllMessagesbycontainer(this.seletedContainer.id).subscribe((data: any) => {
      this.messages = data;
      this.scrolldown() ;
    });
}

  updatemessageContainer(){
    this.chatService.getAllByIdsenderOrAndIdreceiver(this.currentuser.id).subscribe((data :any[]) => {
      console.log('messageContainers1 ')
      console.log(data)
      this.messageContainers1 = data;

      // trying to set the name of the messagecontainer based on the current user

    for (let i = 0; i <this.messageContainers1.length; i++) {
      if(this.messageContainers1[i].idreceiver === this.currentuser.id){
        for (let j = 0; j < this.receivers.length; j++) {
          if(this.receivers[j].id=== this.messageContainers1[i].idsender){
            this.messageContainers1[i]['name'] = this.receivers[j].username ;
          }

        }
        for (let j = 0; j < this.teamlist.length; j++) {

          if(this.teamlist[j].id=== this.messageContainers1[i].idsender){
            this.messageContainers1[i]['name'] = this.teamlist[j].title ;
          }

        }

      } else if(this.messageContainers1[i].idsender === this.currentuser.id){


        for (let j = 0; j < this.receivers.length; j++) {


          if(this.receivers[j].id === this.messageContainers1[i].idreceiver)  {
            this.messageContainers1[i]['name'] = this.receivers[j].username ;
          }

        }

        for (let j = 0; j < this.teamlist.length; j++) {
          if(this.teamlist[j].id=== this.messageContainers1[i].idreceiver){

            this.messageContainers1[i]['name'] = this.teamlist[j].title ;
          }

        }

      }

    }
      this.messageContainers1.reverse();

    this.messageContainers = this.messageContainers1 ;
    }) ;
  }

  //(1)
  // for teams
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.title.toLowerCase().indexOf(filterValue) === 0);
  }
  /// for users
  private _filter1(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.username.toLowerCase().indexOf(filterValue) === 0);
  }
  sentMsg(form: NgForm ) {
    ///console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    console.log(form.value);
    console.log(this.myControl.value) ;
     this.findtheContainer= false ;
    let indexmessageGourp ;

    for (let i = 0; i < this.messageContainers.length; i++) {
      if(this.messageContainers[i].idsender === this.myControl.value.id
        ||this.messageContainers[i].idreceiver === this.myControl.value.id ) {
        this.findtheContainer = true;
        indexmessageGourp = i ;
        break;
      }
    }
    let myDate = new Date();
    if(!this.findtheContainer){
// add  other case with date


      this.chatService.createMessageContainer({
          'idsender':this.currentuser.id ,
          'idreceiver'  :this.myControl.value.id ,
          'last_message' : form.value.messageBody  ,
          'last_message_Date':  this.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ,
        }
        ).subscribe((data: any) =>{

          console.log(data)
        this.message= {
          'message_container_id': data.id,
          'idsender':this.currentuser.id ,
          'idreceiver'  :this.myControl.value.id ,
          'body' :form.value.messageBody  ,
          'vu' : false} ;


        console.log(this.message) ;
        this.chatService.createMessage(this.message).subscribe((data1: any)=>{
          console.log('this.message');
          console.log(data1);

        });
        this.updatemessageContainer() ;

      })

    } else {
      this.messageContainers[indexmessageGourp].last_message = form.value.messageBody ;
      this.messageContainers[indexmessageGourp].last_message_Date = this.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss')  ;
      this.chatService.createMessageContainer(this.messageContainers[indexmessageGourp]).subscribe((data:any)=>  {
        this.message= {
          'message_container_id': this.messageContainers[indexmessageGourp].id,
          'idsender':this.currentuser.id ,
          'idreceiver'  :this.myControl.value.id ,
          'body' :form.value.messageBody  ,
          'vu' : false} ;


        console.log(this.message) ;
        this.chatService.createMessage(this.message).subscribe((data1: any)=>{
          console.log('this.message');
          console.log(data1);
        });
        this.updatemessageContainer() ;

      }) ;


    }



   //websocket this.send(this.receiver.username , this.message) ;
    /*this.chatService.createMessage(this.message).subscribe(data =>{
        console.log(data)
      this.messages.push(this.message) ;
    })*/


  }
  sendMessageinthisContainer(messageform){

    this.message= {
      'message_container_id':this.seletedContainer.id,
      'idsender':this.currentuser.id ,
      'idreceiver' : this.seletedContainer.idreceiver ,
      'body': messageform.value.message  ,
      'vu' : false} ;


    console.log(this.message) ;
    this.chatService.createMessage(this.message).subscribe((data1: any)=>{
      console.log(data1);
      this.messages.push(data1) ;
    });
    this.updatemessageContainer() ;
  }

  handleChangeuserofTeam(event){
    console.log(event.srcElement.value)
    if(event.srcElement.value == 'users'){
      console.log('uuuuuuusers')
      this.options1 = [];
      this.options = [];

      this.options =this.receivers ;
      this.filteredOptions = this.myControl.valueChanges.pipe(
        startWith(''),
        map(value => this._filter1(value))
      );
      this.teamchecked =false  ;
      this.userchecked =true  ;

    } else {

      this.options =this.teamlist ;
      this.filteredOptions = this.myControl.valueChanges.pipe(
        startWith(''),
        map(value => this._filter(value))
      );
      this.teamchecked =true ;
      this.userchecked =false  ;
    }


  }
  openmodal(content){
    this.modal.open(content);
  }

  submitnewMessageContainer(form : any){

}
  send(username:any , message : any ){
    console.log('username '+ username)
    this.stompClient.send('/user/' + username + '/queue/message', {}, JSON.stringify(message));

    console.log('sent looks like ');
  }



  connect1() :any {

    this.url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
    this.socket = new WebSocket(this.url);
    this.stompClient = Stomp.over(this.socket);
    const that = this ;
    this.stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);

      that.stompClient.subscribe('/user/queue/message', function(message) {
        console.log('message has been received ' + message);

        that.addMessage(message.body);
        this.messages.push(this.message) ;

      });
    }) ;
  }
  addMessage(message :any){
    console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    this.message = message
    this.messages.push( JSON.parse(this.message)) ;
    this.scrolldown() ;
  }
 /* openByPersonChat(person: any) {
    this.receiver = person ;
    console.log(this.receiver) ;
    this.chatService.getAllMessages().subscribe(data => {
     console.log('here ')
      console.log(data);

    this.messages = Object.keys(data).map(i => data[i]);

    }) ;

  }*/
  scrolldown(){
    this.messagescontainer.scrollTop =  this.messagescontainer.scrollHeight;
  }
}

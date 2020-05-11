import {Component, Inject, OnInit} from '@angular/core';
import {ChatService} from '../chatlist/chat.service';
import {EventsService} from '../events.service';
import {NgForm} from '@angular/forms';
import {Stomp} from '@stomp/stompjs';
import {DOCUMENT} from '@angular/common';

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
              @Inject(DOCUMENT) private document: Document) { }
  receivers : any ;
  search :any ;
  indivChats: any ;
  receiver : any ;
  currentuser: any ;
  stomp :any ;
  receivedmsg : any ;
  stompClient: any ;
  url : any ;
  socket: any ;
  messagescontainer : any ;
  ngOnInit() {
    //console.log(this.currentuser) ;
    this.messagescontainer =  document.getElementById("messagescontainer");
    this.stomp = this.connect1();
    this.chatService.getuserbyusername(    this.eventservice.currentUserValue.username).subscribe(data =>{
      console.log(data);
      this.currentuser =data;
    }) ;
      this.chatService.getIndivHistoric().subscribe(data => {
        console.log('indiv ')
        console.log(data)
        this.indivChats = Object.keys(data).map(i => data[i]);
      }) ;
      this.eventservice.getDoctors().subscribe(data => {
        this.receivers = Object.keys(data).map(i => data[i]);
      });
    this.scrolldown()

  }

  sentMsg(form: NgForm ) {
    ///console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    console.log(form.value.message);
    this.message= {
      'idsender':this.currentuser.id ,
      'idreceiver'  :this.receiver.id ,
      'body' :form.value.message ,
      'vu' : false} ;

    this.send(this.receiver.username , this.message) ;
    this.chatService.createMessage(this.message).subscribe(data =>{
        console.log(data)
      this.messages.push(this.message) ;
    })


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

      });
    }) ;
  }
  addMessage(message :any){
    console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    this.message = message
    this.messages.push( JSON.parse(this.message)) ;
    this.scrolldown() ;
  }
  openByPersonChat(person: any) {
    this.receiver = person ;
    console.log(this.receiver) ;
    this.chatService.getAllMessages().subscribe(data => {
     console.log('here ')
      console.log(data);

    this.messages = Object.keys(data).map(i => data[i]);

    this.scrolldown() ;
    }) ;

  }
  scrolldown(){
    this.messagescontainer.scrollTop =  this.messagescontainer.scrollHeight;
  }
}

import { Component, OnInit } from '@angular/core';
import {ChatService} from './chat.service';
import {EventsService} from '../events.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
messages: any ;
  private message: any;
  constructor(private chatService: ChatService,
              private  eventservice: EventsService) { }
  receivers : any ;
  search :any ;
  indivChats: any ;
  receiver : any ;
  currentuser: any ;
  ngOnInit() {
    //console.log(this.currentuser) ;
    this.chatService.getuserbyusername(    this.eventservice.currentUserValue.username).subscribe(data =>{
      console.log(data);
      this.currentuser =data;
    })
      this.chatService.getIndivHistoric().subscribe(data => {
        console.log('indiv ')
        console.log(data)
        this.indivChats = Object.keys(data).map(i => data[i]);
      }) ;
      this.eventservice.getDoctors().subscribe(data => {
        this.receivers = Object.keys(data).map(i => data[i]);
      });
  }

  sentMsg(form: NgForm ) {
    ///console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    console.log(form.value.message);
    this.message= {'idsender':this.currentuser.id ,
      'idreceiver'  :this.receiver.id ,
      'body' :form.value.message ,
      'vu' : false} ;
    this.chatService.createMessage(this.message).subscribe(data =>{
        console.log(data)
      this.messages.push(this.message) ;
    })

  }
  openByPersonChat(person: any) {
    this.receiver = person ;
    console.log(this.receiver) ;
  this.chatService.getAllMessages().subscribe(data => {
     console.log('here ')
      console.log(data);

    this.messages = Object.keys(data).map(i => data[i]);
    }) ;

  }
}

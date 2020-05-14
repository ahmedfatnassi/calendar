import { Component, OnInit } from '@angular/core';
import {ChatService} from './chat.service';
import {EventsService} from '../events.service';

@Component({
  selector: 'app-chatlist',
  templateUrl: './chatlist.component.html',
  styleUrls: ['./chatlist.component.css']
})
export class ChatlistComponent implements OnInit {

  constructor( private  eventservice: EventsService ,
               private chatService: ChatService) { }

  receivers: any ;
  conversations : any[]  ;
  messages:any ;
  ngOnInit() {
   //  this.conversations.push();
    this.eventservice.getDoctors().subscribe(data => {
      this.receivers = Object.keys(data).map(i => data[i]);

      this.chatService.getAllMessages().subscribe(data => {
        this.messages = Object.keys(data).map(i => data[i]);
      /*for (let i = 0; i <this.receivers.length; i++) {
        for (let j = 0; j <  this.messages.length; j++) {
          if (this.messages.idsender === this.receivers.id ||this.messages.idreceiver === this.receivers.id){
             this.addconversation(
               this.receivers[j].name ,
             this.receivers[j].familyname ,
             this.receivers[j].send_date ,
             this.receivers[j].body) ;
              continue ;
          }
        }
      }*/
      console.log(this.conversations) ;
      });
    });
  }
  addconversation(name ,firstname ,date ,body){
  /*this.conversations.push({
    name ,
    firstname ,
    date,
    body
  }) ;*/
  }

}

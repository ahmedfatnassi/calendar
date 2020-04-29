import { Injectable } from '@angular/core';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';
import { Subject } from 'rxjs';
import { Observable } from 'rxjs';
import {Stomp} from '@stomp/stompjs';
import * as url from 'url';
import {EventsService} from '../events.service';
import * as SockJS from 'sockjs-client' ;
import * as io from 'socket.io-client';

import * as Rx from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class NotificationhandlerService {
  stompClient: any ;
  url : any ;
  socket: any ;

  constructor(private eventservice: EventsService ) {
  }

  connect1() :any{

     this.url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
     this.socket = new WebSocket(url);
     this.stompClient = Stomp.over(this.socket);
     this.stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);
      const that = this ;
      return that.stompClient ;
      /*that.stompClient.subscribe('/user/queue/reply', function(message) {
        console.log('Error websocket' + message);
      });*/
    }) ;
  }

}

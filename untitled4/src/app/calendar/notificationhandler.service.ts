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
  constructor(private eventservice: EventsService ) {
  }

  connect1():any{
    const url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
    const socket = new WebSocket(url);
    const stompClient = Stomp.over(socket);
     stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/user/queue/reply', function(message) {
        console.log('Error websocket' + message);
        return stompClient ;
      });
    return stompClient ;
    }) ;
     return stompClient;
  }

}

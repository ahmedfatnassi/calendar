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
  private socket;


  //connect(): Rx.Subject<MessageEvent> {
    // If you aren't familiar with environment variables then
    // you can hard code `environment.ws_url` as `http://localhost:5000`
  //  = io('ws://localhost:8080/greeting/websocket' ,'ws');
  /*  this.socket  = io('http://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080', {path:'/greeting/websocket' ,transportOptions: {
        polling: {
          extraHeaders: {
            'Content-Type' :  'application/json',
            'Authorization' : 'Basic ' + btoa( this.eventservice.currentUserValue.username + ':' +  this.eventservice.currentUserValue.password)
          }
        }
      } });*/
    //this.socket = new WebSocket('ws://localhost:8080/greeting/websocket');

   // this.socket.connect()
    // We define our observable which will observe any incoming messages
    // from our socket.io server.
    /*const  observable = new Observable(observer => {
      this.socket.onopen( '/greeting/websocket',(data) => {
        console.log("Received message from Websocket Server")
        observer.next(data);
      })
      return () => {
        this.socket.disconnect();
      }
    });*/

  //this.socket.subscribe()
    // We define our Observer which will listen to messages
    // from our other components and send messages back to our
    // socket server whenever the `next()` method is called.
    /*let observer = {
      next: (data: Object) => {
        this.socket.emit('message', JSON.stringify(data));
      },
    };*/

    // we return our Rx.Subject which is a combination
    // of both an observer and observable.
    //return Rx.Subject.create(observer, observable);
 // }

  private subject: Rx.Subject<MessageEvent>;

  public connect(): Rx.Subject<MessageEvent> {
    if (!this.subject) {
      this.subject = this.create('ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket');
      console.log('Successfully connected: ');
    }
    return this.subject;
  }

  private create(url): Rx.Subject<MessageEvent> {
    this.socket = new WebSocket(url);

    const observable = Rx.Observable.create((obs: Rx.Observer<MessageEvent>) => {
      this.socket.onmessage = obs.next.bind(obs);
      this.socket.onerror = obs.error.bind(obs);
      this.socket.onclose = obs.complete.bind(obs);
      return this.socket.close.bind(this.socket);
    });
    let observer = {
      next: (data: Object) => {
        if (this.socket.readyState === WebSocket.OPEN) {
          this.socket.send(JSON.stringify(data));
        }
      }
    };
    return Rx.Subject.create(observer, observable);
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

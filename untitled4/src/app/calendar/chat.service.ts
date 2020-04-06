import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';
import {NotificationhandlerService} from './notificationhandler.service';
import {map} from 'rxjs/operators';
import {EventsService} from '../events.service';
export interface Message {
  author: string;
  message: string;
}
@Injectable({
  providedIn: 'root'
})

export class ChatService {

  messages: Subject<any>;

  // Our constructor calls our wsService connect method
  constructor(private wsService: NotificationhandlerService) {
    this.messages = <Subject<Message>>wsService.connect().pipe(map(
      (response: MessageEvent): Message => {
        let data = JSON.parse(response.data);
        return {
          author: data.author,
          message: data.message
        };
      }
    ));

  }

  // Our simplified interface for sending
  // messages back to our socket.io server
  sendMsg(msg) {
    this.messages.next(msg);
  }
}

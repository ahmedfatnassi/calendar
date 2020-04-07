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

  }

  // Our simplified interface for sending
  // messages back to our socket.io server

}

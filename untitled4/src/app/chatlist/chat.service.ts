import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventsService} from '../events.service';
import {Stomp} from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient ,private eventservice: EventsService) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',

    })
  };

  getMessagesBysenderID(id : any) {
    return   this.http.get<any[]>('http://localhost:8080/messages/'+id, this.httpOptions) ;

  }
  getAllMessages() {
    return   this.http.get<any[]>('http://localhost:8080/messages', this.httpOptions) ;

  }
  createMessage(value: any ) {
    return this.http.post('http://localhost:8080/messages' , value , this.httpOptions) ;
  }
  getIndivHistoric() {
    return   this.http.get<any[]>('http://localhost:8080/individualChatHistory', this.httpOptions) ;

  }
  createIndivHist(value: any ) {
    return this.http.post('http://localhost:8080/individualChatHistory' , value , this.httpOptions) ;
  }
  getIndivById(id: any ) {
    return   this.http.get<any[]>('http://localhost:8080/individualChatHistory/byid/' + id, this.httpOptions) ;

  }
  getIndivBypersonid(id: any ) {
    return   this.http.get<any[]>('http://localhost:8080/individualChatHistory/byreceiverid/' + id, this.httpOptions) ;

  }
  getuserbyusername(username: any) {
    return   this.http.get<any[]>('http://localhost:8080/doctors/' + username, this.httpOptions) ;

  }
}

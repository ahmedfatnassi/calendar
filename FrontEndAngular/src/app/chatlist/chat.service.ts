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
  getAllMessagesbycontainer(id) {
    return   this.http.get<any[]>('http://localhost:8080/messages/container/'+id, this.httpOptions) ;

  }
  createMessage(value: any ) {
    return this.http.post('http://localhost:8080/messages' , value , this.httpOptions) ;
  }
  getAllMessagesContainer() {
    return   this.http.get<any[]>('http://localhost:8080/message_container', this.httpOptions) ;

  }
  getAllByIdsenderOrAndIdreceiver(id : any ) {
    return   this.http.get<any[]>('http://localhost:8080/message_container/'+id, this.httpOptions) ;

  }

  createMessageContainer(value: any ) {
    return this.http.post('http://localhost:8080/message_container' , value , this.httpOptions) ;
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
  getAlTeamsbyEmployeeID(id: any ) {
    return   this.http.get<any[]>('http://localhost:8080/teams/' + id, this.httpOptions) ;

  }
  getuserbyusername(username: any) {
    return   this.http.get<any[]>('http://localhost:8080/persons/' + username, this.httpOptions) ;

  }
  getMessageContainerbyteamsIDs(teamsIDs: any[]){
    return this.http.get<any[]>('http://localhost:8080/message_container/ContainerTeams' , { params :
        {'teamsIDs': teamsIDs }
    });

  }
}

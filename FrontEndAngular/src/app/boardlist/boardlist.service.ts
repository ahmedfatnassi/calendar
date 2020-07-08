import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BoardlistService {

  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',

    })
  };
  getBoards() {
    return   this.http.get<any[]>('http://localhost:8080/boards', this.httpOptions) ;

  }
  create(value: any ){
    return this.http.post('http://localhost:8080/boards' , value , this.httpOptions) ;
  }
}

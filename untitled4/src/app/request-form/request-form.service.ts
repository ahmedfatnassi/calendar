import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequestFormService {

  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',

    })
  };
  getinsured(value: any ){
    return this.http.get('http://localhost:8080/insureds/' + value  , this.httpOptions) ;
  }
}

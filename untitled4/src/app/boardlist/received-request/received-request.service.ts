import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReceivedRequestService {

  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',

    })
  };
  getAllRequest() {
    return   this.http.get<any[]>('http://localhost:8080/requests/', this.httpOptions) ;

  }


  getAllActsByRequestsId(requestId: any) {
    const params = new HttpParams();
    params.set('requestIds', JSON.stringify([1])) ;
    return   this.http.get<any[]>('http://localhost:8080/acts/requestIds', { params :
        {'requestIds' : requestId }
    }) ;

  }
  getAllInsuredsByRequestsId(requestId: any) {
    const params = new HttpParams();
    params.set('requestIds', JSON.stringify([1])) ;
    return   this.http.get<any[]>('http://localhost:8080/insureds/requestIds', { params :
        {'requestIds' : requestId }
    }) ;

  }
}

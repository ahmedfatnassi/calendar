import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

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
  private httpOptions1: { headers: HttpHeaders };
  getinsured(value: any ) {
    return this.http.get('http://localhost:8080/insureds/' + value  , this.httpOptions) ;
  }
  createRequest(value: any ) {
    return this.http.post('http://localhost:8080/requests/' , value , this.httpOptions) ;
  }
  createActs(value: any ) {
    return this.http.post('http://localhost:8080/acts/' , value , this.httpOptions) ;
  }
  persistFiles(file: FormData ) {

    const headers = new HttpHeaders();
    this.httpOptions1 = {
      headers: new HttpHeaders({
      'Content-Type': undefined ,
        Accept : 'application/json'

      })
    };
    headers.append(  'Content-Type' , 'multipart/form-data') ;
    headers.append(  'Accept' , 'application/json') ;
    console.log('passed undefined ')
  //  headers.append('Accept', 'application/json' );
    /*, {  observe: 'response' }); /*{ headers : new HttpHeaders(
      { 'Content-Type':  'application/json',
        responseType: 'json'}) ,
      reportProgress: true}  ) ;*/
    const params = new HttpParams();

    return this.http.post('http://localhost:8080/uploadFile',  file , {

      reportProgress: true,
      responseType: 'text'
    } ) ;
  }
}

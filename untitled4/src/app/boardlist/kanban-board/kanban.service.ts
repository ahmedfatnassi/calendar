import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KanbanService {
  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',

    })
  };
  getColumns(id: any) {
    return this.http.get<any[]>('http://localhost:8080/columns/' + id, this.httpOptions);
  }
  createColumn(value: any ){
    return this.http.post('http://localhost:8080/columns' , value , this.httpOptions) ;
  }

  createtask( columnid : any ){
    return this.http.post('http://localhost:8080/columns' , columnid     , this.httpOptions) ;
  }
}

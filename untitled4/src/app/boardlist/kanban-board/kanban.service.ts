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
  getTasks(id: any) {
    return this.http.get<any[]>('http://localhost:8080/tasks/' + id, this.httpOptions);
  }
  getColumns(id: any) {
    return this.http.get<any[]>('http://localhost:8080/columns/' + id, this.httpOptions);
  }

  createColumn(value: any ){
    return this.http.post('http://localhost:8080/columns' , value , this.httpOptions) ;
  }

  createtask(task: any ){
    return this.http.post('http://localhost:8080/tasks', task    , this.httpOptions) ;
  }
  updateColumnAfterDeplacement(tasks:any) {
    return this.http.post<any[]>('http://localhost:8080/tasks/updatecolumn/', tasks,this.httpOptions);
  }
  getTasksColumnsIds(columnIds: any[]){
    return this.http.get<any[]>('http://localhost:8080/tasks/get_task_by_columnsIds' , { params :
        {'columnIds': columnIds }
    });

  }
  getallEmployee() {
    return   this.http.get<any[]>('http://localhost:8080/employees', this.httpOptions) ;
  }
  getEmployeeById(id: any) {
    return this.http.get<any[]>('http://localhost:8080/employees/getById/' + id, this.httpOptions);
  }
}

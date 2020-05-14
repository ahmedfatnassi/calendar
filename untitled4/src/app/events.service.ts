import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  token: string;

}

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }



  public get currentUserValue(): User {
    console.log('current user ')
    console.log( this.currentUserSubject.value )
    return this.currentUserSubject.value;
  }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',

    })
    };


  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;



  getDoctors() {
    return   this.http.get<any[]>('http://localhost:8080/doctors', this.httpOptions) ;

  }
  getPatients() {
    return   this.http.get<any[]>('http://localhost:8080/patients', this.httpOptions) ;

  }
  getAgents() {
    return   this.http.get<any[]>('http://localhost:8080/agents', this.httpOptions) ;

  }
  getEvents() {
    return   this.http.get<any[]>('http://localhost:8080/events', this.httpOptions) ;

  }
  getAllusers() {
    return   this.http.get<any[]>('http://localhost:8080/persons', this.httpOptions) ;

  }
  PostEvents(newEvent: any) {
    return   this.http.post<any[]>('http://localhost:8080/events', newEvent, this.httpOptions)
      .subscribe(data => {
        console.log(data) ;
      }) ;

  }
  getEventsByreceiver(id) {
    return   this.http.get<any[]>('http://localhost:8080/events/doctor_event/' + id, this.httpOptions) ;

  }


  login(username: string, password: string) {
    return this.http.post<any>('http://localhost:8080/login', { username, password })
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        console.log('lam heeeere login success') ;

       if (user && user.token) {
          console.log('login stored in ')

          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        }

        return user;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.http.post<any>('http://localhost:8080/logout', null)
      .pipe(map(user => {} ))  ;
  }
};

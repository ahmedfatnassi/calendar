import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EventsService} from '../events.service';

@Injectable({
  providedIn: 'root'
})
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: EventsService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // add authorization header with jwt token if available
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser && currentUser.token) {
      console.log("sent with success" +currentUser.username + currentUser.password)
      request = request.clone({
        setHeaders: {
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( currentUser.username + ':' +  currentUser.password)
        } ,

      });
    }

    return next.handle(request);
  }
}

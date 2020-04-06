import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {EventsService} from '../events.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Stomp} from '@stomp/stompjs';
import {NotificationhandlerService} from '../calendar/notificationhandler.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private eventservice: EventsService , private route: ActivatedRoute,
              private router: Router , private notification: NotificationhandlerService ) {

    if (this.eventservice.currentUserValue) {
      this.router.navigate(['/home']);
    }

  }
  loading = false;
  submitted = false;
  ngOnInit() {
  }
  submitLogin(form: NgForm) {
    console.log(form.value) ;


    this.submitted = true;

    this.loading = true;
    const  url = 'ws://localhost:8080/greeting/websocket' ;

    this.eventservice.login(form.value.username , form.value.password).subscribe(
      data => {
       // this.notification.connect();
        console.log(data) ;
        this.router.navigate(['/home']);
        // tslint:disable-next-line:prefer-const
      /*  const headers = {
          login: data.username,
          passcode:  data.password ,
          // additional header
          'client-id': data.token
        };
        const client = Stomp.client(url);
        // tslint:disable-next-line:only-arrow-functions
        client.connect(headers, function(message) {
          console.log('login to socket ' + message.body);
        });*/

      }


    ) ;
  }

}

import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {EventsService} from '../events.service';
import {error} from 'util';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private eventservice: EventsService , private route: ActivatedRoute,
              private router: Router ) {

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

    this.eventservice.login(form.value.username , form.value.password).subscribe(
      data => {
        console.log(data) ;
        this.router.navigate(['/home']);
      }


    ) ;
  }

}

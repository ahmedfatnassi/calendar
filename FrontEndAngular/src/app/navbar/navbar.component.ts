import { Component, OnInit } from '@angular/core';
import {EventsService} from '../events.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private eventservice: EventsService , private router: Router ) { }
showNavBar : boolean ;
  ngOnInit( ) {
    if(this.eventservice.currentUserValue  === null) {
          this.showNavBar = true;
    } else {
      this.showNavBar = false ;

    }

  }
  logout() {
    console.log('logout') ;
    this.eventservice.logout() ;
    this.router.navigate(['/login'])
      .then(() => {
        window.location.reload();
      });
  }

}

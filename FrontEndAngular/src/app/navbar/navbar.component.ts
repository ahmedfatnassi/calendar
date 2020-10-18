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
isadmin : boolean ;
  ngOnInit( ) {
    this.isadmin = false ;
    if(this.eventservice.currentUserValue  === null) {
          this.showNavBar = true;
    } else {
      this.showNavBar = false ;

    }
    if(this.eventservice.currentUserValue.type === 'Administrator') {
      this.isadmin = true;
    }
  }
  logout() {
    console.log('logout') ;
    this.eventservice.logout() ;
    this.router.navigate(['/login']).then(() => {
      window.location.reload();
    }); ;

  }
goTolink(link: any){
  console.log('this.eventservice.currentUserValue');
  console.log(this.eventservice.currentUserValue);
  this.router.navigate([link]) ;
  ;
}
}

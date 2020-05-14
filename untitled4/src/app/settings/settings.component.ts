import { Component, OnInit } from '@angular/core';
import {SettingsService} from './settings.service';
import {NgForm} from '@angular/forms';
import {log} from 'util';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  visibileAddEmployee : boolean ;
  updatedEmplyee : any;
  employees: any  ;
  teams : any ;
  subscription : any ;
  openteamlist : any ;
  ListEmplyeeByTeam :any ;
  ListEmployeenotInTeam: any ;
  updateOrClose :any ;
  constructor(private settingsService: SettingsService) { }

  ngOnInit() {
    this.updateOrClose = 'update';
    this.visibileAddEmployee = false ;
    this.employees = [] ;
    this.settingsService.getallEmployee().subscribe(data => {
      this.employees = Object.keys(data).map(i => data[i]);
      console.log(data) ;
    });
    this.settingsService.getAllTeams().subscribe(data => {
      console.log(data)
      this.teams = Object.keys(data).map(i => data[i]);
    });
  }
  employerformVisible(){
    if (this.visibileAddEmployee === true) {
      this.visibileAddEmployee = false ;
    } else {
      this.visibileAddEmployee = true;
    }
  }
  submitEmployee(form: NgForm ) {
  console.log(form.value);
  this.settingsService.createemplyee(
    {
      matricule: form.value.matricule,
      name : form.value.name,
      familyname: form.value.familyname ,
      username : form.value.username,
      cin : form.value.cin,
      address : form.value.address,
      password : form.value.password
    }).subscribe(data => {
      console.log(data) ;
      this.employees.push(data);
  }) ;
  }
  deleteEmployee(item: any ) {
    console.log('id= ' + item)
    this.settingsService.deletebyid(item.id).subscribe(() => {
      console.log('nothing') ;
      const index = this.employees.indexOf(item, 0);
      if (index > -1) {
        this.employees.splice(index, 1);
      }
    });
  }

  update(){
  this.employerformVisible() ;
  }
  submitTeam(form: NgForm ){
      this.settingsService.createTeam({ 'title' : form.value.title}).subscribe(data =>{
        console.log(data);
        this.teams.push(data) ;
      }) ;
  }
  // get employee in team and than create array that contains employees not in that team
  showsubscriptionList(item: any ) {
    this.settingsService.getAllSubscprition(item.id).subscribe(data => {
      this.ListEmplyeeByTeam = Object.keys(data).map(i => data[i]);
      console.log(data);
      if ( !(this.openteamlist === item.title) ) {
      this.openteamlist = item.title ;
      } else {
        this.openteamlist = "";
      }
      this.ListEmployeenotInTeam = [];
      if( this.ListEmplyeeByTeam.length === 0 ){

        for (let i = 0; i < this.employees.length; i++) {
          this.ListEmployeenotInTeam.push(this.employees[i]) ;
        }
      } else {
        let findid = false  ;
        for (let i = 0; i < this.employees.length; i++) {
          findid =false ;
          for (let j = 0; j < this.ListEmplyeeByTeam.length; j++) {

            if (this.ListEmplyeeByTeam[j].id === this.employees[i].id) {
              //console.log('welcome');
              findid = true ;
              continue;
              //
            }
          }
          if (!findid ) {
            this.ListEmployeenotInTeam.push(this.employees[i]);
            continue;
          }
        }
      }
      //console.log(this.ListEmployeenotInTeam);
    }) ;
  }
subscribeEmployertoteam(idteam: any , iduser: any) {
    console.log(idteam + ' slqksfo ' + iduser)
    this.settingsService.createSubcription({
      idTeam : idteam,
      idUser : iduser
    }).subscribe(data => {
      console.log(' subcription')
      console.log(data) ;
      for (let i = 0; i < this.ListEmployeenotInTeam.length ; i++) {
        if (this.ListEmployeenotInTeam[i].id === iduser  ) {
          console.log('yesssssss')
          this.ListEmplyeeByTeam.push(this.ListEmployeenotInTeam[i]);
          this.ListEmployeenotInTeam.splice(i, 1);
          console.log(this.ListEmployeenotInTeam)
          console.log(this.ListEmplyeeByTeam)
          break;
        }
      }
    });

}
unsubscribe(idteam: any , iduser: any ) {
    console.log(idteam +'  '+iduser) ;
    this.settingsService.deletesubscirptionbyidTeamAndidUser(idteam , iduser).subscribe(data => {
        console.log(data);
      for (let i = 0; i < this.ListEmplyeeByTeam.length ; i++) {

        if (this.ListEmplyeeByTeam[i].id === iduser  ) {
          console.log('yesssssss')
            this.ListEmployeenotInTeam.push(this.ListEmplyeeByTeam[i]);
          this.ListEmplyeeByTeam.splice(i, 1);
          console.log(this.ListEmployeenotInTeam)
          console.log(this.ListEmplyeeByTeam) ;
            break;
        }
      }
    }) ;
}
}

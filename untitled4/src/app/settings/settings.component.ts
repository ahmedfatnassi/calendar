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
  constructor(private settingsService: SettingsService) { }

  ngOnInit() {
    this.visibileAddEmployee = false ;
    this.employees = [] ;
    this.settingsService.getallEmployee().subscribe(data => {
      this.employees = Object.keys(data).map(i => data[i]);
    });
    this.settingsService.getAllTeams().subscribe(data => {
      this.teams = Object.keys(data).map(i => data[i]);
    });
  }
  employerformVisible(){
    if(this.visibileAddEmployee===true){
      this.visibileAddEmployee =false
    }else{
      this.visibileAddEmployee= true;
    }
  }
  submitEmployee(form: NgForm ) {
  console.log(form.value);
  this.settingsService.createemplyee(
    {
      matricule:form.value.matricule,
      name :form.value.name,
      familyname:form.value.familyname ,
      username :form.value.username,
      cin :form.value.cin,
      address :form.value.address,
      password :form.value.password
    }).subscribe(data =>{
      console.log(data) ;
      this.employees.push(data);
  }) ;
  }
  deleteEmployee(item :any ){
    console.log('id= '+item)
    this.settingsService.deletebyid(item.id).subscribe(()=>{
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
      this.settingsService.createTeam({'title' : form.value.title}).subscribe(data =>{
        console.log(data);
        this.teams.push(data)
      })
  }

}

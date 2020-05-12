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
  constructor(private settingsService: SettingsService) { }
  employees: any  ;
  ngOnInit() {
    this.visibileAddEmployee = false ;
    this.employees = [] ;
    this.settingsService.getallEmployee().subscribe(data => {
      this.employees = Object.keys(data).map(i => data[i]);
      console.log(data);
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
  this.settingsService.create(
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
  }) ;
  }
  deleteEmployee(id :any ){
    console.log('id= '+id)
    this.settingsService.deletebyid(id).subscribe(()=>{
      console.log('nothing')
    });
  }

}

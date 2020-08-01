import { Component, OnInit } from '@angular/core';
import {SettingsService} from './settings.service';
import {NgForm} from '@angular/forms';
import {log} from 'util';
import {BoardlistService} from '../boardlist/boardlist.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {KanbanService} from '../boardlist/kanban-board/kanban.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  visibileAddEmployee : boolean ;
  visibileAdddoctors : boolean ;
  updatedEmplyee : any;
  employees: any  ;
  doctors: any  ;
  teams : any ;
  subscription : any ;
  openteamlist : any ;
  ListEmplyeeByTeam :any ;
  ListEmployeenotInTeam: any ;
  updateOrClose :any ;
  Listboard : any ;
  listBoardinworkspace: any ;
  listBoardNotinWorkspace :any ;
  openworkspaceList :any ;
  openColumnList: any ;
  ListColumns :any ;
  openColumnBoardID: any ;
  constructor(private settingsService: SettingsService ,
              private  boardlistService: BoardlistService,
              private modal: NgbModal ,
              private kanbanBoard: KanbanService,
  ) { }

  ngOnInit() {
    this.updateOrClose = 'update';
    this.visibileAddEmployee = false ;
    this.visibileAdddoctors = false ;
    this.employees = [] ;
    this.doctors = [] ;
    this.settingsService.getallEmployee().subscribe(data => {
      this.employees = Object.keys(data).map(i => data[i]);
      console.log(data) ;
    });
     this.settingsService.getallDoctor().subscribe(data => {
      this.doctors = Object.keys(data).map(i => data[i]);
      console.log(data) ;
    });
    this.boardlistService.getBoards().subscribe((data:any) => {
      this.Listboard = Object.keys(data).map(i => data[i]);
      this.listBoardNotinWorkspace = JSON.parse(JSON.stringify(this.Listboard)) ;

    }) ;
    this.settingsService.getAllTeams().subscribe(data => {
      console.log(data)
      this.teams = Object.keys(data).map(i => data[i]);
    });

  }
  addToWorkSpace(team: any , board: any ) {
    this.settingsService.createworkspace({idteam : team.id  , idboard : board.id}).subscribe((data) =>  {
      for (let i = 0; i < this.listBoardNotinWorkspace.length; i++) {
        if (this.listBoardNotinWorkspace[i].id === board.id){
          this.listBoardinworkspace.push(this.listBoardNotinWorkspace[i]) ;
          this.listBoardNotinWorkspace.splice(i,1);
          break ;
        }
      }    }) ;
  }
  employerformVisible(){
    if (this.visibileAddEmployee === true) {
      this.visibileAddEmployee = false ;
    } else {
      this.visibileAddEmployee = true;
    }
  }
  doctorformVisible(){
    if (this.visibileAdddoctors === true) {
      this.visibileAdddoctors = false ;
    } else {
      this.visibileAdddoctors = true;
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
  submitDoctor(form: NgForm ) {
    console.log(form.value);
    this.settingsService.createDoctor(
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
  deleteDoctor(item: any ) {
    console.log('id= ' + item)
    this.settingsService.deleteDoctorbyid(item.id).subscribe(() => {
      console.log('nothing') ;
      const index = this.doctors.indexOf(item, 0);
      if (index > -1) {
        this.doctors.splice(index, 1);
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
      if ( !(this.openteamlist === item.id) ) {
      this.openteamlist = item.id ;
      this.openworkspaceList = "";
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
  showWorkspace(team: any) {
    console.log(team.id) ;
    this.settingsService.getAllworkspaces(team.id).subscribe((data:any )=>{
      this.listBoardinworkspace = Object.keys(data).map(i => data[i]);
      this.listBoardNotinWorkspace= [] ;
      this.listBoardNotinWorkspace = JSON.parse(JSON.stringify(this.Listboard)) ;

      let findit = false ;
      for (let i = 0; i < this.listBoardNotinWorkspace.length; i++) {
        if(findit){
          i=i-1 ;
          findit= false ;
        }
        for (let j = 0; j < this.listBoardinworkspace.length; j++) {

          if (this.listBoardNotinWorkspace[i].id === this.listBoardinworkspace[j].id) {
            this.listBoardNotinWorkspace.splice(i,1) ;
            findit = true ;
            continue;
          }
        }
      }
      console.log(this.listBoardinworkspace) ;
      console.log(this.listBoardNotinWorkspace);

    }) ;
    if ( !(this.openworkspaceList === team.id) ) {
      this.openworkspaceList = team.id ;
      this.openteamlist = "";
    } else {
      this.openworkspaceList = "";
    }

  }
  deleteBoardFromWorkspace( team: any , board :any ){
    this.settingsService.deleteBoardFromTeamWorkpaceid(team.id , board.id).subscribe((data :any )=>{
      console.log(data) ;
      for (let i = 0; i < this.listBoardinworkspace.length; i++) {
        if (this.listBoardinworkspace[i].id === board.id){
            this.listBoardNotinWorkspace.push(this.listBoardinworkspace[i]) ;
            this.listBoardinworkspace.splice(i,1);
            break ;
        }
      }

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
deleteTeam(team: any ) {
    this.settingsService.deleteTeamsbyidTeam(team.id).subscribe(() => {
      console.log('yes')
      for (let i = 0; i <this.teams.length ; i++) {
        if(this.teams[i] === team ){
          this.teams.splice(i,1);
        }
      }

    });
}
showBoard(board: any ) {

this.settingsService.getAllcolumnsByBoardId(board.id).subscribe((data: any ) => {
  console.log(board.id)
  if (! (this.openColumnList === board.id)) {
    this.openColumnList = board.id ;
  } else {
    this.openColumnList = null;
  }
  this.ListColumns = data ;
}) ;
}
deleteColumnmByid(column :any ){
    this.settingsService.deleteColumnByid(column.id).subscribe(() => {
          console.log('was deleted') ;
    }) ;
}
updateColumn(column :any ){
    this.settingsService.updateColumn(column).subscribe((data :any ) => {
      console.log(column);
    })
}
openCreateEmployeeModal(content){
  const modalRef = this.modal.open(content);

}
  createNewBoard(form: NgForm){
    this.boardlistService.create({name : form.value.name}).subscribe(data => {

      this.Listboard.push(data);

    }) ;
  }
  createColumn(form: NgForm) {
    const name = form.value.name ;
    this.kanbanBoard.createColumn({
      name: form.value.name,
      boardId: this.openColumnBoardID
    }).subscribe((data:any) => {
      console.log(data)

    });

    this.modal.dismissAll();
  }
  openCreateColumnModal(form : any ,boardid : any){
    this.openColumnBoardID = boardid;
      this.modal.open(form);

  }
}

import { Component, OnInit } from '@angular/core';
import {ReceivedRequestService} from './received-request.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';
import {EventsService} from '../../events.service';
import {BoardlistService} from '../boardlist.service';
import {KanbanService} from '../kanban-board/kanban.service';

@Component({
  selector: 'app-received-request',
  templateUrl: './received-request.component.html',
  styleUrls: ['./received-request.component.css']
})

export class ReceivedRequestComponent implements OnInit {
  requests: any[] ;
  listRequestIds: any[];
  listInsuredId: any[] ;
  boardList: any[];
  radioSelected: any ;
  boardIsSelectedid: any ;
  columnsList: any ;
  constructor(private receivedRequestService: ReceivedRequestService,
              private modal: NgbModal,
              private boardService: BoardlistService,
              private kanbanBoard: KanbanService,) { }

  ngOnInit() {
    this.listRequestIds = [];
    this.listInsuredId = [];
    this.requests = [];
    this.boardList = [];
    this.boardService.getBoards().subscribe((boards: any[]) =>{
      console.log(boards) ;
      this.boardList = boards ;
    });
    this.receivedRequestService.getAllRequest().subscribe((requests: any ) => {
      console.log(requests);
      this.requests = requests ;
      for (let i = 0; i < requests.length; i++) {
        this.listRequestIds.push(requests[i].id);
        this.requests[i]['acts']=[];

      }
      this.receivedRequestService.getAllActsByRequestsId(this.listRequestIds).subscribe((acts: any ) => {
        console.log('salem');
        console.log(acts);
        for (let i = 0; i < requests.length; i++) {

          for (let j = 0; j < acts.length; j++) {
            acts[j].type = acts[j].type.toLowerCase()
            acts[j].type = acts[j].type.split('_').join(' ');
            if(this.requests[i].id ===  acts[j].requestId ){
            this.requests[i]['acts'].push( acts[j]);}
          }
        }

        for (let i = 0; i < this.requests.length; i++) {
            if(this.listInsuredId.indexOf(this.requests[i].insuredID)===-1){
              this.listInsuredId.push(this.requests[i].insuredID) ;
            }
        }
        this.receivedRequestService.getAllInsuredsByRequestsId(this.listInsuredId).subscribe((insureds: any ) =>{
          for (let i = 0; i < requests.length; i++) {

            for (let j = 0; j < insureds.length; j++) {

              if(this.requests[i].insuredID ===  insureds[j].id ){
                this.requests[i]['insured'] =  insureds[j];
              }
            }
          }
        }) ;


        console.log('this.requests');
        console.log(this.requests);
      }) ;
    }) ;
  }
  OpenModalColumn(form: NgForm,  requestId: any ) {
    console.log(requestId) ;
    this.modal.open(form);
  }
  execute(form: NgForm ) {
    console.log(form.value);
    this.modal.dismissAll() ;

  }
  checkedBoard(boardid: any){

this.kanbanBoard.getColumns(boardid).subscribe((data: any[] )=>{
  this.columnsList= data;
  console.log(this.columnsList);
});
    this.boardIsSelectedid = boardid;

  }
}

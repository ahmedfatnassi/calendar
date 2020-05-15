import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {KanbanService} from './kanban.service';
import {NgForm} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EventsService} from '../../events.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-kanban-board',
  templateUrl: './kanban-board.component.html',
  styleUrls: ['./kanban-board.component.css']
})
export class KanbanBoardComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private kanbanBoard: KanbanService,
              private modal: NgbModal,
              private eventserviceService: EventsService) {
  }
  boardid: any ;
  id: any;
  columns: any;
  idOpenedTask: any;
  doctors
  color: any ;
  tasks : any ;
  fullcolumn : any ;
  fullBoard : any ;
  task : any ;
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.boardid = params['id'];
      this.kanbanBoard.getColumns(this.boardid).subscribe(data => {
        console.log('columns')
        console.log(data)
        this.columns = Object.keys(data).map(i => data[i]);
        console.log('columns ' + this.columns);
        this.kanbanBoard.getTasks(this.boardid).subscribe(taskdata => {
          console.log('salem')
          console.log(taskdata)
          this.tasks = Object.keys(taskdata).map(i => taskdata[i]);

          /*for (let i = 0; i < this.columns.length ; i++) {
           for (let j = 0; j < this.tasks.length ; j++) {
          if (this.tasks[j].columnID === this.columns[i].id) {
              this.fullBoard[this.columns[i].id]=this.tasks[j];
          }
          }
        }*/


        console.log(taskdata);
         //console.log(this.fullBoard)
        });
      } )

      this.eventserviceService.getDoctors().subscribe(data => {
        this.doctors = Object.keys(data).map(i => data[i]);
        console.log(this.doctors);

      });


    });
  }
  //https://stackblitz.com/angular/dqglperyxxp?file=src%2Fapp%2Fcdk-drag-drop-disabled-sorting-example.html
  drop(event: CdkDragDrop<string[]>) {
    console.log(event);
    if (event.previousContainer === event.container) {
      console.log('hello')
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else { // problem because we have same data array
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }

  }
  createColumn(form: NgForm) {
    this.kanbanBoard.createColumn({
      name: form.value.name,
      boardId: this.boardid
    }).subscribe(data => {
      console.log('columns : ' );
      console.log(data)
      console.log(this.boardid)
      this.columns.concat({
        name: form.value.name,
        boardId: this.boardid
      });
    });
    this.columns.push({
      name: form.value.name,
      boardId: this.boardid
    })
    this.modal.dismissAll();
  }

  OpenModalColumn(form) {
    this.modal.open(form);
  }

  OpenModalTask(form, id: any) {
    this.idOpenedTask = id;
    console.log(id)
    this.modal.open(form);
  }

  createTask(form: NgForm) {
  this.task = {
    title: form.value.title,
    assignedUser : ''+form.value.assignedUser.id,
    color: this.color,
    description: form.value.description ,
    columnID: this.idOpenedTask ,
    boardID  : this.boardid
  }
    this.kanbanBoard.createtask(this.task).subscribe(data => {
      console.log( data);
      this.modal.dismissAll();
    });
  this.tasks.push(this.task);
  }
}

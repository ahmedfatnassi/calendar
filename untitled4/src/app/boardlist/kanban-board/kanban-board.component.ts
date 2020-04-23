import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {KanbanService} from './kanban.service';
import {NgForm} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-kanban-board',
  templateUrl: './kanban-board.component.html',
  styleUrls: ['./kanban-board.component.css']
})
export class KanbanBoardComponent implements OnInit {

  constructor(private route: ActivatedRoute ,
              private kanbanBoard: KanbanService ,
              private modal: NgbModal) { }
  id: any;
  columns: any ;
  idOpenedTask : any;
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.kanbanBoard.getColumns(this.id).subscribe(data => {
        console.log(data)
        this.columns = Object.keys(data).map(i => data[i]);
        console.log('columns ' + this.columns) ;
      }) ;

    });
  }
  create(form: NgForm){
    this.kanbanBoard.createColumn({name: form.value.name,
      boardId : this.id} ).subscribe(data =>{
        console.log('columns : ' + data) ;
        this.columns.concat({name: form.value.name,
          boardId : this.id}) ;
    }) ;
  }
  OpenModalColumn(form){
    this.modal.open(form);
  }
  OpenModalTask(form , id: any ) {
    this.idOpenedTask = id ;
    console.log(id)
    this.modal.open(form  );
  }
}

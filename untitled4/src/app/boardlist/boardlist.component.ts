import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';
import {BoardlistService} from './boardlist.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-boardlist',
  templateUrl: './boardlist.component.html',
  styleUrls: ['./boardlist.component.css']
})
export class BoardlistComponent implements OnInit {

  constructor(private  modal: NgbModal ,
              private  boardlistService: BoardlistService ,
              private router: Router
  ) { }
boards: any[] ;
  ngOnInit() {
    this.boardlistService.getBoards().subscribe(data => {
      this.boards = Object.keys(data).map(i => data[i]);
      console.log(this.boards) ;
    }) ;
  }

  openForm(form){
    this.modal.open(form);
  }
  createNewBoard(form: NgForm){
    this.boardlistService.create({name : form.value.name}).subscribe(data => {
      console.log('boards = '+data) ;
      this.boards.push(data);
      this.modal.dismissAll()
    }) ;
  }
  goToBoard(id){
    this.router.navigate(['/board/'+id]);
  }
}

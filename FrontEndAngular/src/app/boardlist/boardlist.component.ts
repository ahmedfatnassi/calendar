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
    this.boards = [] ;
    this.boardlistService.getBoards().subscribe((data: any ) => {
      console.log(data)
      this.boards =  data ;
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
  //  console.log('id of board ' +id)
    this.router.navigate(['/board/'+id]);
  }
  goToRequestList(){
      this.router.navigate(['/received_request']);
  }
  goToProcessedRequest(){
      this.router.navigate(['/processed_request']);
  }
}

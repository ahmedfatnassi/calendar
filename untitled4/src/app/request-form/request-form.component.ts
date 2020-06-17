import { Component, OnInit } from '@angular/core';
import {RequestFormService} from './request-form.service';
import {NgForm} from '@angular/forms';
import {stringify} from 'querystring';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {error} from 'util';

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {
actNumber: any ;
authentifiedInsured: boolean;
  Insuredusername: any ;
passwrod: any;
insuredData: any ;
listOfAct: any ;
act: any ;
request: any  ;
files: any ;
  fileToUpload: any ;
  requestdata :any ;
  constructor(private  requestFormService: RequestFormService ,
              private  modal: NgbModal ) { }
  qrResultString: string;

  ngOnInit() {
    this.files = new Array(1)
    this.actNumber = new Array(1);
    this.authentifiedInsured = false ;
  }
addAct() {
  this.actNumber = new Array(this.actNumber.length + 1);
  this.files.push(null);
  }
deleteAct(index: any) {
    console.log(index);
    this.actNumber.splice(index , 1) ;
}
sendRequest() {
this.modal.dismissAll() ;
console.log(this.insuredData.id) ;
this.request[ 'insuredID' ] = this.insuredData.id ;
this.requestFormService.createRequest(this.request).subscribe((request: any ) => {
  console.log('request :')
  console.log(request )
  this.requestdata = request ;
  for (let i = 0; i < this.listOfAct.length; i++) {
    this.listOfAct[i]['requestId'] = this.requestdata.id ;
  }
  this.requestFormService.createActs(this.listOfAct).subscribe((acts: any) => {
  console.log('acts') ;
  console.log(acts) ;
  });

}) ;
  }
  clearResult(): void {
    this.qrResultString = null;
  }

  onCodeResult(resultString: string) {
    this.qrResultString = resultString;
    console.log(resultString);
    const limit = this.qrResultString.indexOf(':');
    this.Insuredusername = this.qrResultString.substring(0 , limit);
    this.passwrod = this.qrResultString.substring(limit + 1 , this.qrResultString.length);
    console.log(this.Insuredusername) ;
    console.log(this.passwrod) ;
    this.requestFormService.getinsured(this.Insuredusername ).subscribe((data: any) => {
      console.log(data) ;
      console.log()
      if (data ) {
        this.authentifiedInsured =  true ;
        this.insuredData = data  ;
      }
    }) ;
  }
  openForm(form){
    this.modal.open(form);
  }
  retrieveRequest(form: NgForm) {

    this.listOfAct = [];
    for (let j = 0; j < this.actNumber.length; j++) {
      this.act = {};

      for (const i in form.value) {
        if (JSON.stringify(i).indexOf(JSON.stringify(j)) >= 0) {
          //console.log(form.value[i]) ;
          this.act[i.substring(0, i.indexOf('-'))] = form.value[i];
        }

      }
      // console.log(this.act);
      this.listOfAct.push(this.act);
    }
    console.log('this.listOfAct');
    console.log(this.listOfAct);
    this.request = {};
    for (const i in form.value) {
      if (JSON.stringify(i).indexOf('-') === -1) {
        this.request[i] = form.value[i];
      }
    }
    console.log('request') ;
    console.log(this.request);
    //this.handleFileInput(JSON.parse(JSON.stringify(event.target)).files );
    //let formData: FormData = new FormData();


    for (let i = 0; i < this.files.length; i++) {
      const formData: FormData = new FormData();
     // const file = new File([this.files[i]], this.files[i].name ,{type: "text/json;charset=utf-8"});

      formData.set('file',  this.files[i] );
      console.log('formData' );
      console.log(this.files[i].value) ;

    //  console.log(formData );

     // console.log(this.files[i] );
   /* this.requestFormService.persistFiles( formData).subscribe(
      (data: any) => {
      console.log('data data salem' );
    },
    ( error: any) => {
      console.log('error');
      console.log(error);
      });*/
  }
  }
//
  handleFileInput(files: any  , oldindex: any ) {
    this.fileToUpload = files.item(0);
    this.files[oldindex] = this.fileToUpload;
    console.log(this.files) ;
  }

}

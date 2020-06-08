import { Component, OnInit } from '@angular/core';
import {RequestFormService} from './request-form.service';
import {NgForm} from '@angular/forms';
import {stringify} from 'querystring';

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
  constructor(private  requestFormService: RequestFormService) { }
  qrResultString: string;

  ngOnInit() {
    this.actNumber = new Array(1);
    this.authentifiedInsured = true ;
  }
addAct() {
  this.actNumber = new Array(this.actNumber.length + 1);
  }
deleteAct(index: any) {
    console.log(index);
    this.actNumber.splice(index , 1) ;
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
      if (data != null) {

        this.authentifiedInsured =  true ;
      }
    }) ;
  }

  submitrequest(form: NgForm) {
    //console.log(form.value);
    this.act = {};
    for (let j = 0; j < this.actNumber.length; j++) {
      for (const i in form.value) {
            if (JSON.stringify(i).indexOf(JSON.stringify(j)) >= 0) {
              //console.log(form.value[i]) ;
              this.act[i.substring(0,i.indexOf('-'))] = form.value[i] ;
            }

      }
      ///console.log(this.act);

    }
  }
}

import { Component, OnInit } from '@angular/core';
import {RequestFormService} from './request-form.service';

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {
actNumber: any ;
authentifiedInsured: boolean
  Insuredusername: any ;
passwrod: any;
insuredData: any ;
  constructor(private  requestFormService: RequestFormService) { }
  qrResultString: string;

  ngOnInit() {
    this.actNumber = new Array(0);
    this.authentifiedInsured = false ;
  }
addAct() {
  this.actNumber = new Array(this.actNumber.length +1);
  }
deleteAct(index: any){
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
      if (data != null){
        this.authentifiedInsured =  true ;
      }
    }) ;
  }
}

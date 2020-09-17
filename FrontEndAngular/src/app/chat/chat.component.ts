import {Component, Inject, OnInit} from '@angular/core';
import {ChatService} from '../chatlist/chat.service';
import {EventsService} from '../events.service';
import {FormControl, NgForm} from '@angular/forms';
import {Stomp} from '@stomp/stompjs';
import {DatePipe, DOCUMENT} from '@angular/common';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {SettingsService} from '../settings/settings.service';
import {forkJoin, Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
messages: any ;
  keyword :any ;
  private message: any;
  private receiverId: any;
  private receiverMessage: any;
  constructor(private chatService: ChatService,
              private  eventservice: EventsService ,
              @Inject(DOCUMENT) private document: Document ,
              private modal: NgbModal ,
              private settingsService: SettingsService ,
              public datepipe: DatePipe ,) { }
  receivers : any ;
  search :any ;
  indivChats: any ;
  receiver : any ;
  currentuser: any ;
  stomp :any ;
  receivedmsg : any ;
  stompClient: any ;
  url : any ;
  teamlist: any ;
  listOfTeamContainer: any;
  socket: any ;
  messagescontainer : any ;
  //https://stackblitz.com/angular/lrkvvylgqal?file=src%2Fapp%2Fautocomplete-auto-active-first-option-example.ts (1)
  myControl = new FormControl();
  options: any[] ;
  options1 :any[];
  messageContainers1:any ;messageContainers:any ;
  filteredOptions: Observable<string[]>;
  userchecked : boolean
  teamchecked : boolean
  seletedContainer:any ;
  listtype: string ;
  lastmsgreceiver: any;
  findtheContainer:boolean ;
  ngOnInit() {
    //console.log(this.currentuser) ;
    //(1)
    this.seletedContainer = {id:-1};
    this.teamchecked =true ;
    this.userchecked =false  ;
    this.messagescontainer =  document.getElementById("messagescontainer");

    this.chatService.getuserbyusername(    this.eventservice.currentUserValue.username).subscribe(data =>{
      console.log(data);
      this.currentuser =  data;
      this.stomp = this.connect1();
      this.chatService.getAllTeamsbyEmployeeID(this.currentuser.id).subscribe((teams: any[] ) => {
        console.log('data');
        console.log(teams);
        this.teamlist = teams;
        // tslint:disable-next-line:prefer-const
        let teamsids =[] ;
        for (let i = 0; i < teams.length; i++) {
            teamsids.push(teams[i].id) ;
          }
        console.log('teamsids= ')
        console.log(teamsids)
        this.chatService.getMessageContainerbyteamsIDs(teamsids).subscribe((get:any[])=>{
          console.log('get')
          console.log(get);
          this.listOfTeamContainer = get ;
          this.messageContainers1= get ;
          console.log(this.messageContainers1);

          this.teamlist = teams ;
          this.options1 = [] ;
          for (let i = 0; i < this.teamlist.length; i++) {
            this.options1.push(this.teamlist[i].title) ;
          }
          this.teamchecked =true ;
          this.userchecked =false  ;
          this.keyword = 'title' ;
          this.options =this.teamlist ;

        })





      this.eventservice.getAllusers().subscribe(data => {
        console.log('doctors ') ;
        console.log(data);
        this.receivers = Object.keys(data).map(i => data[i]);






        this.chatService.getAllByIdsenderOrAndIdreceiver(this.currentuser.id).subscribe((messageContainers3 :any[]) => {
          console.log('messageContainers3 ')
          console.log(messageContainers3)
          this.messageContainers1 = messageContainers3;
          for (let i = 0; i < messageContainers3.length; i++) {
            let find = false ;

            //console.log(this.messageContainers1.indexOf(messageContainers3[i]) === '-1' )
            for (let j = 0; j < this.messageContainers1.length; j++) {
              if((this.messageContainers1[j].id === messageContainers3[i].id )){
                 find = true  ;
              }
            }
            if( !find){
              this.messageContainers1.push(messageContainers3[i]) ;
            }
          }
        console.log('this.messageContainers1')
        console.log(this.messageContainers1)
          this.updatemessageContainer() ;

          // trying to set the name of the messagecontainer based on the current user
       //   this.updatemessageContainer();
        }) ;

      });

    }) ;
    });
    this.scrolldown();

  }
seletContainer(container:any){
    this.seletedContainer = container ;

    console.log(this.seletedContainer) ;
    this.chatService.getAllMessagesbycontainer(this.seletedContainer.id).subscribe((data: any) => {
      this.messages = data;
      this.scrolldown() ;
    });
}

  updatemessageContainer(){

      // trying to set the name of the messagecontainer based on the current user

    for (let i = 0; i <this.messageContainers1.length; i++) {
      if(this.messageContainers1[i].sender_Type === 'users') {
        if (this.messageContainers1[i].idreceiver === this.currentuser.id) {
          for (let j = 0; j < this.receivers.length; j++) {
            if (this.receivers[j].id === this.messageContainers1[i].idsender) {
              this.messageContainers1[i]['name'] = this.receivers[j].username;
            }

          }
          for (let j = 0; j < this.teamlist.length; j++) {

            if (this.teamlist[j].id === this.messageContainers1[i].idsender) {
              this.messageContainers1[i]['name'] = this.teamlist[j].title;
            }

          }

        } else if (this.messageContainers1[i].idsender === this.currentuser.id) {


          for (let j = 0; j < this.receivers.length; j++) {


            if (this.receivers[j].id === this.messageContainers1[i].idreceiver) {
              this.messageContainers1[i]['name'] = this.receivers[j].username;
            }

          }

          for (let j = 0; j < this.teamlist.length; j++) {
            if (this.teamlist[j].id === this.messageContainers1[i].idreceiver) {

              this.messageContainers1[i]['name'] = this.teamlist[j].title;
            }

          }

        }
      }else {
        for (let j = 0; j < this.teamlist.length; j++) {

          if (this.teamlist[j].id === this.messageContainers1[i].idsender) {
            this.messageContainers1[i]['name'] = this.teamlist[j].title;
          }

        }
        for (let j = 0; j < this.teamlist.length; j++) {
          if (this.teamlist[j].id === this.messageContainers1[i].idreceiver) {

            this.messageContainers1[i]['name'] = this.teamlist[j].title;
          }

        }


      }

    }
      this.messageContainers1.reverse();
      this.messageContainers1.sort((a, b) => new Date(b.last_message_Date).getTime() - new Date(a.last_message_Date).getTime())
    this.messageContainers = this.messageContainers1 ;

  }

  //(1)
  // for teams
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.title.toLowerCase().indexOf(filterValue) === 0);
  }
  /// for users
  private _filter1(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.username.toLowerCase().indexOf(filterValue) === 0);
  }
  sentMsg(form: NgForm ) {



    ///console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    console.log(form.value);
    console.log(this.receiverMessage) ;
     this.findtheContainer= false ;
    let indexmessageGourp ;

    for (let i = 0; i < this.messageContainers.length; i++) {
      if((this.messageContainers[i].idsender === this.receiverMessage.id && this.messageContainers[i].idreceiver === this.currentuser.id)
        || (this.messageContainers[i].idreceiver === this.receiverMessage.id && this.messageContainers[i].idsender === this.receiverMessage.id) ) {
        this.findtheContainer = true;
        indexmessageGourp = i ;
        break;
      }else {
        if(this.messageContainers[i].idreceiver === this.receiverMessage.id) {
          this.findtheContainer = true;
          indexmessageGourp = i ;
          break;
        }
      }
    }
    let myDate = new Date();
    console.log(this.findtheContainer)
    if ( !this.findtheContainer){
// add  other case with date


      this.chatService.createMessageContainer({
          'sender_Type': form.value.customRadio ,
          'idsender':this.currentuser.id ,
          'idreceiver'  :this.receiverMessage.id ,
          'last_message' : form.value.messageBody  ,
          'last_message_Date':  this.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ,
        }
        ).subscribe((data: any) =>{

        this.message= {
          'message_container_id': data.id,
          'idsender':this.currentuser.id ,
          'idreceiver'  :this.receiverMessage.id ,
          'body' :form.value.messageBody  ,
          'vu' : false} ;


        console.log(this.message) ;
        this.chatService.createMessage(this.message).subscribe((data1: any)=>{
          console.log('this.message');
          console.log(data1);
          this.messages.push(data1) ;


        });
        this.updatemessageContainer() ;

      })

    } else {
      this.messageContainers[indexmessageGourp].last_message = form.value.messageBody ;
      this.messageContainers[indexmessageGourp].last_message_Date = this.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss')  ;
      this.chatService.createMessageContainer(this.messageContainers[indexmessageGourp]).subscribe((data:any)=>  {
        this.message= {
          'message_container_id': this.messageContainers[indexmessageGourp].id,
          'sender_Type': this.messageContainers[indexmessageGourp].sender_Type ,
          'idsender':this.currentuser.id ,
            'idreceiver'  :this.receiverMessage.id ,
          'body' :form.value.messageBody  ,
          'vu' : false} ;


        console.log(this.message) ;
        this.chatService.createMessage(this.message).subscribe((data1: any)=>{
          console.log('this.message');
          console.log(data1);
          this.messages.push(data1) ;


        });
        this.updatemessageContainer() ;

      }) ;


    }
    this.getuserfromId(this.seletedContainer.idreceiver)

    this.sendtouser(this.lastmsgreceiver.username , this.message) ;




    /*this.chatService.createMessage(this.message).subscribe(data =>{
        console.log(data)
      this.messages.push(this.message) ;
    })*/


  }
  getuserfromId(id: any){
    for (let i = 0; i < this.receivers.length; i++) {
        if(this.receivers[i].id === id ){
          console.log( this.receivers[i]) ;
          this.lastmsgreceiver = this.receivers[i];
          break;
        }
    }

  }
  sendMessageinthisContainer(messageform){

    this.message= {
      'message_container_id':this.seletedContainer.id,
      'idsender':this.currentuser.id ,
      'idreceiver' : this.seletedContainer.idreceiver ,
      'body': messageform.value.message  ,
      'vu' : false} ;


    console.log(this.message) ;
    const myDate = new Date();
    this.seletedContainer.last_message = messageform.value.message ;
    this.seletedContainer.last_message_Date = this.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss')  ;

    this.chatService.createMessageContainer(this.seletedContainer).subscribe((data: any) => {
      console.log(data)

    this.chatService.createMessage(this.message).subscribe((data1: any)=>{
      console.log(data1);
      this.messages.push(data1) ;
      let sendtoteam ;
      if(this.seletedContainer.sender_Type === 'teams'){
        for (let i = 0; i < this.teamlist.length; i++) {
          if(this.teamlist[i].id === this.seletedContainer.idreceiver){
             sendtoteam = this.teamlist[i] ;
            break;
          }
        }
        this.sendToteam(sendtoteam.title, this.message) ;
      }else {
        this.getuserfromId(this.seletedContainer.idreceiver);

        this.sendtouser(this.lastmsgreceiver.username, this.message);
      }
      //this.send( , this.message) ;

    });
    this.updatemessageContainer() ;
    });
  }

  handleChangeuserofTeam(event){
    //console.log(event.srcElement.value)
    if(event.form.value.customRadio  == 'users') {
      console.log('uuuuuuusers')
      this.options1 = [];
      this.options = [];



      this.teamchecked =false  ;
      this.userchecked =true  ;
      this.keyword = 'username'
      this.options =this.receivers ;
    } else {



      this.teamchecked =true ;
      this.userchecked =false  ;
      this.keyword = 'title' ;
      this.options =this.teamlist ;

    }


  }
  openmodal(content){
    this.modal.open(content);
    this.chatService.getAllTeamsbyEmployeeID(this.currentuser.id).subscribe((data: any[] ) => {
      console.log('data');
      console.log(data);
      this.teamlist = data;

    });
  }

  submitnewMessageContainer(form : any){

}
  sendtouser(username:any , message : any ){
    console.log('username '+ username)
    this.stompClient.send('/user/' + username + '/queue/message', {}, JSON.stringify(message));

    console.log('sent looks like ');
  }
  sendToteam(teamtitle:any , message : any ){
    this.stompClient.send( '/queue/broadcast/'+teamtitle, {}, JSON.stringify(message));

  }



  connect1() :any {

    this.url = 'ws://' + this.eventservice.currentUserValue.username+':'+this.eventservice.currentUserValue.password+ '@' +
      'localhost:8080/greeting/websocket' ;
    this.socket = new WebSocket(this.url);
    this.stompClient = Stomp.over(this.socket);
    const that = this ;
    this.stompClient.connect(this.eventservice.currentUserValue.username,this.eventservice.currentUserValue.password , function(frame) {
      console.log('Connected: ' + frame);
      that.chatService.getAllTeamsbyEmployeeID(that.currentuser.id).subscribe((data: any[] ) => {
        console.log('data');
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          that.stompClient.subscribe('/queue/broadcast/' + data[i].title, function(message) {
            that.addMessage(message);
          });
        }
      });
      that.stompClient.subscribe('/user/queue/message', function(message) {

        that.addMessage(message);



        //that.seletedContainer.last_message = message.body ;
        //that.seletedContainer.last_message_Date = that.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss')  ;
       // this.messages.push(this.message) ;



      });
    }) ;
  }
  addMessage(message :any){
    console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
    this.message = JSON.parse(message.body)
    //console.log(message)
    if(!(this.messages === undefined)){
      this.messages.push(this.message) ;
    }

    const myDate = new Date();
    for (let i = 0; i < this.messageContainers.length; i++) {
      console.log('message.message_container_id');
      console.log(this.message.message_container_id);

      if(this.messageContainers[i].id === this.message.message_container_id){

        this.messageContainers[i].last_message = this.message.body ;
        this.messageContainers[i].last_message_Date = this.datepipe.transform(myDate, 'yyyy-MM-dd' + 'T' + 'HH:mm:ss') ;
        console.log(this.messageContainers[i])
        break ;
      }
    }
    this.scrolldown() ;
  }
 /* openByPersonChat(person: any) {
    this.receiver = person ;
    console.log(this.receiver) ;
    this.chatService.getAllMessages().subscribe(data => {
     console.log('here ')
      console.log(data);

    this.messages = Object.keys(data).map(i => data[i]);

    }) ;

  }*/
  scrolldown(){
    this.messagescontainer.scrollTop =  this.messagescontainer.scrollHeight;
  }
  selectEvent(item) {
    // do something with selected item
    console.log('item')
    console.log(item)
    this.receiverMessage  = item;
  }

  onChangeSearch(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.

  }

  onFocused(e){
    // do something when input is focused
  }
}

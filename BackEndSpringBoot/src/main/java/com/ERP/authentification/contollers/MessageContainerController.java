package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.Message;
import com.ERP.authentification.Models.MessageContainer;
import com.ERP.authentification.Models.Request;
import com.ERP.authentification.services.MessageContainerService;
import com.ERP.authentification.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message_container")
public class MessageContainerController {
    @Autowired
    private MessageContainerService messageContainerService ;
    @Autowired
    private MessageService messageService ;
    @PostMapping
    public ResponseEntity<MessageContainer> create(@RequestBody MessageContainer messageContainer  ) {
        return ResponseEntity.status(201).body(this.messageContainerService.create(messageContainer));
    }
    @GetMapping
    public ResponseEntity<List<MessageContainer>> getNonExecutedRequestAll(){

        return ResponseEntity.ok().body(messageContainerService.findAll() ) ;
    }
    @GetMapping("/ContainerTeams")
    public ResponseEntity<List<MessageContainer>> getAllContainerByTeamIdList(@RequestParam("teamsIDs") List<Long>  ids){

        return ResponseEntity.ok().body(messageContainerService.findAllTeamMessageContainer(ids) ) ;
    }
    @GetMapping("{id}")
        public ResponseEntity<List<MessageContainer>> getAllByIdsenderOrAndIdreceiver(@PathVariable Long id){
        //  this.messageService.findTopMessagebysenderId(id);

        return ResponseEntity.ok().body(messageContainerService.findAllByIdsenderOrAndIdreceiver(id)) ;
    }
    @GetMapping("deleteAll")
    public void deleteAll(){
        this.messageContainerService.deleteAll();
        this.messageService.deleteAll();

    }

}

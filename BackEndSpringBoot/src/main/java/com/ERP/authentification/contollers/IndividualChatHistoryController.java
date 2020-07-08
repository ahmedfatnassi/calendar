package com.ERP.authentification.contollers;

import com.ERP.authentification.Models.BoardColumn;
import com.ERP.authentification.Models.IndividualChatHistory;
import com.ERP.authentification.Models.Patient;
import com.ERP.authentification.services.IndividualChatHistoryService;
import com.ERP.authentification.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/individualChatHistory")
public class IndividualChatHistoryController  {
    @Autowired
    private IndividualChatHistoryService individualChatHistoryService ;

    @PostMapping
    public ResponseEntity<IndividualChatHistory> create(@RequestBody IndividualChatHistory individualChatHistory   ) {

        return ResponseEntity.status(201).body(this.individualChatHistoryService.create(individualChatHistory));
    }
    @GetMapping
    public ResponseEntity<List<IndividualChatHistory >> getAll(){
        return ResponseEntity.ok().body(individualChatHistoryService.findAll()) ;
    }
    @GetMapping(value = "/byid/{id}")
    public ResponseEntity<Optional<IndividualChatHistory>> getByIndivId(@PathVariable Long id)  {
        return ResponseEntity.ok().body(individualChatHistoryService.findAllById(id));
    }
    @GetMapping(value = "/byreceiverid/{id}")
    public ResponseEntity<Optional<IndividualChatHistory>> getAllByReceiverId(@PathVariable Long id)  {
        return ResponseEntity.ok().body(individualChatHistoryService.findByPerson(id));
    }
}

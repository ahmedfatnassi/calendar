package com.ERP.authentification.contollers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.RequestContextHolder;

import com.ERP.authentification.services.SecurityServiceImp;
import com.google.gson.Gson;

@Controller
@CrossOrigin()
public class WebSocketController {
 
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private SecurityServiceImp securityService;
  
    @MessageMapping("/message")
    @SendToUser("/queue/reply")
    public Map<String,String> processMessageFromClient(@Payload Map<String,String> map1, Principal principal, @Header("simpSessionId") String sessionId  , @Headers Map<String,String> map ) throws Exception {
    	
     System.out.println("map1 "+map1);
    	return  map1;
    }
    
    
    @MessageMapping("/broadcast")
    @SendTo("/queue/broadcast")
    public Map<String,String> breadcast(@Payload Map<String,String> map1, Principal principal, @Header("simpSessionId") String sessionId  , @Headers Map<String,String> map ) throws Exception {
    	
     System.out.println("map1 "+map1);
    	return  map1;
    }
    
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
       }
     
    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
  
}
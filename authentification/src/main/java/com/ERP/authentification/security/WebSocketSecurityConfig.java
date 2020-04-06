package com.ERP.authentification.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketSecurityConfig
extends AbstractSecurityWebSocketMessageBrokerConfigurer { 

    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
        .nullDestMatcher().authenticated() 
        .simpSubscribeDestMatchers("/user/queue/errors").permitAll() 
        .simpDestMatchers("/app/**").hasRole("USER") 
        .simpSubscribeDestMatchers("/queue/**", "/topic/*").hasRole("USER") 
        .anyMessage().denyAll(); 

    }
}
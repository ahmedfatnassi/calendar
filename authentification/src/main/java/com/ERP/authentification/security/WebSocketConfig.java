package com.ERP.authentification.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.ERP.authentification.services.UserHandShakeHandle;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan
public class WebSocketConfig
  extends AbstractWebSocketMessageBrokerConfigurer {
     
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic/", "/queue/");
    config.setApplicationDestinationPrefixes("/app");
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
      registration.setInterceptors(new ChannelInterceptorAdapter() {

          @Override
          public Message<?> preSend(Message<?> message, MessageChannel channel) {

              StompHeaderAccessor accessor =
                  MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

              if (StompCommand.CONNECT.equals(accessor.getCommand())) {
               // System.out.println("message= "+ accessor.getLogin() +" destination  "+accessor.getDestination()  +" channel= " + channel.toString() );
                	 //ArrayList<GrantedAuthority> ga= new ArrayList<>() ;
                	// ga.add( new SimpleGrantedAuthority("USER")) ;
            	    Principal user = new Principal() {
						
						@Override
						public String getName() {
							
							return accessor.getLogin();
						}
					};
                    accessor.setUser(user);
              }

              return message;
          }
      });
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	registry
    	  .addEndpoint("/greeting")
    	  .setAllowedOrigins("*")
    	//  .setHandshakeHandler(new UserHandShakeHandle())
    	  .withSockJS();    }   
    
    
    
    
    
}
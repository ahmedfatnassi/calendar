package com.ERP.authentification.services;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class UserHandShakeHandle {}/*extends DefaultHandshakeHandler {
@Autowired
public SecurityServiceImp securityServiceImp ;
    @Override
    protected Principal determineUser(ServerHttpRequest request,
            WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal principal = request.getPrincipal(); 
        	SecurityContextHolder.getContext().getAuthentication() ;
			System.out.println("handshakehandle "+wsHandler +" r "+request.getHeaders().getFirst("cookie") + " principle  " +principal +" security " +SecurityContextHolder.getContext().getAuthentication() );
			
      /*  try {
			System.out.println(request.getBody().toString()  + " sdksg  " + request.getHeaders());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
      /*  return principal;

    }
    public boolean beforeHandshake(
            ServerHttpRequest request, 
            ServerHttpResponse response, 
            WebSocketHandler wsHandler,
            Map attributes) throws Exception {
    		System.out.println("before request ");

                if (request instanceof ServletServerHttpRequest) {
                	System.out.println("in request ");
                    ServletServerHttpRequest servletRequest
                     = (ServletServerHttpRequest) request;
                    HttpSession session = servletRequest
                      .getServletRequest().getSession();
                    attributes.put("sessionId", session.getId());
                }
                    return true;
            }

}*/
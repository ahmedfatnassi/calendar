package com.ERP.authentification.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.ERP.authentification.Models.Medecin;

@Service
public class SecurityServiceImp   {
	  @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private MyUserDetailsService userDetailsService;


	    public String findLoggedInUsername() {
	        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        System.out.println(userDetails);
	        if (userDetails instanceof UserDetails) {
	        	 System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails()) ;
	            return ((UserDetails)userDetails).getUsername();
	        }

	        return null;
	    }

	    
	    public void autoLogin(String username, String password) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

	        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            System.out.println("success authenticated "+usernamePasswordAuthenticationToken);
	        }
	    }
}

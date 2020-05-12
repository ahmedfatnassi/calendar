package com.ERP.authentification.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.ERP.authentification.Models.Event;
import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Person;
import com.ERP.authentification.Models.UserLoginForm;
import com.ERP.authentification.services.SecurityServiceImp;

import lombok.RequiredArgsConstructor;
@CrossOrigin()
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
    private SecurityServiceImp securityService;
	 @Autowired
	    BCryptPasswordEncoder bCryptPasswordEncoder;
	@PostMapping
	public  ResponseEntity<?>  login( @RequestBody  UserLoginForm medecin ) {
		
		securityService.autoLogin(medecin.getUsername(), medecin.getPassword());
       System.out.println((RequestContextHolder.currentRequestAttributes().getSessionId()));
       medecin.setToken((RequestContextHolder.currentRequestAttributes().getSessionId()));
		return ResponseEntity.status(200).body(medecin);
	}
}

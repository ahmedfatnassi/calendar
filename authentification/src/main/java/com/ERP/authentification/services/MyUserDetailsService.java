package com.ERP.authentification.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ERP.authentification.Models.Medecin;
import com.ERP.authentification.Models.Person;
import com.ERP.authentification.repositories.MedecinRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
    	System.out.println(username);
        Medecin medecin = medecinRepository.findByUsername(username).get();
		System.out.println(medecin.getPassword()+" "+bCryptPasswordEncoder.encode(medecin.getPassword()));
        if (medecin == null) {
            throw new UsernameNotFoundException(username);
        }
       
        boolean enabled = true;  
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        ArrayList<GrantedAuthority> ga= new ArrayList<>() ;
        ga.add( new SimpleGrantedAuthority("USER")) ;
        return new User(
        		medecin.getUsername(),
        		bCryptPasswordEncoder.encode(medecin.getPassword()),
        		enabled ,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                ga );
        }

    }

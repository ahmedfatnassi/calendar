package com.ERP.authentification.services;

import java.util.ArrayList;

import com.ERP.authentification.repositories.PersonRepository;
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
    private PersonRepository personRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        Person person = personRepository.findByUsername(username).get();
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
       
        boolean enabled = true;  
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        ArrayList<GrantedAuthority> ga= new ArrayList<>() ;
        ga.add( new SimpleGrantedAuthority("USER")) ;
        return new User(
        		person.getUsername(),
        		bCryptPasswordEncoder.encode(person.getPassword()),
        		enabled ,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                ga );
        }

    }

package com.sinotrans.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinotrans.role.entity.SinoRole;
import com.sinotrans.user.entity.SinoUser;
import com.sinotrans.user.repository.SinoUserRepository;

import lombok.var;

@Service
@Transactional
public class SinoUserDetailsService implements UserDetailsService{

    @Autowired
    private SinoUserRepository sinoUserRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var probe = new SinoUser();
        probe.setUsername(username);
        
        var userOptional = sinoUserRepository.findOne(Example.of(probe));
        var user = userOptional.orElseThrow(() -> new UsernameNotFoundException("unknown username"));
        
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        for (SinoRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}

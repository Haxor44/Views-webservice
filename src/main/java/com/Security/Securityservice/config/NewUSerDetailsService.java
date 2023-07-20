package com.Security.Securityservice.config;

import com.Security.Securityservice.entity.Users;
import com.Security.Securityservice.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NewUSerDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Users> users1 = userRepository.findByName(username);
        return users1.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}

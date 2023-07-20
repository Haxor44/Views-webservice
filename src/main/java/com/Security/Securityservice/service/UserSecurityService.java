package com.Security.Securityservice.service;

import com.Security.Securityservice.entity.Users;
import com.Security.Securityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public String save(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added successfully";
    }

    public String generateToken(String username){
        return jwtService.generateToken((username));
    }

    public void validateToken(String token){
        jwtService.validateToken((token));
    }
}

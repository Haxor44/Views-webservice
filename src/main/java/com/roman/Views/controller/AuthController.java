package com.roman.Views.controller;


import com.roman.Views.model.AppUser;
import com.roman.Views.dto.AuthenticationRequest;
import com.roman.Views.repository.UserRepository;
import com.roman.Views.security.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/*
* A class that provides the endpoints dealing with authentication
*
* */

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTutil jwTutil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registration(@RequestBody AppUser appUser){
        String encodePass = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePass);
        appUser = userRepository.save(appUser);
        String token = jwTutil.generateToken(appUser.getEmail());
        return Collections.singletonMap("jwt-token",token);
    }


    @PostMapping("/login")
    public  Map<String, Object> login(@RequestBody AuthenticationRequest authenticationRequest){
        try
        {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword());
            authenticationManager.authenticate(authToken);
            String token = jwTutil.generateToken(authenticationRequest.getEmail());

            return Collections.singletonMap("jwt-token",token);

        } catch (AuthenticationException authEcep)
        {
            throw new RuntimeException("Invalid credentials!!!");
        }
    }



}

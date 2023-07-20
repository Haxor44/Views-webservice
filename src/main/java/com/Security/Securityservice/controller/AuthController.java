package com.Security.Securityservice.controller;


import com.Security.Securityservice.dto.AuthReq;
import com.Security.Securityservice.entity.Users;
import com.Security.Securityservice.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://roman-frontend.vercel.app/",allowCredentials="true")
public class AuthController {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")

    public String newUser(@RequestBody Users user){
        return userSecurityService.save(user);
    }


    @PostMapping("/token")
    public String getToken(@RequestBody AuthReq users){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
        if (authentication.isAuthenticated()){
            return userSecurityService.generateToken(users.getUsername());
        }
        else{
            throw  new RuntimeException("not valid!!!");
        }

    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        userSecurityService.validateToken(token);
        return "Valid token!!!";
    }
}

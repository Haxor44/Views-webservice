package com.roman.Views.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

//This class is to generate and validate tokens
@Component
public class JWTutil {
    private String secret = "78214125442A462D4A614E645267556B58703273357638792F423F4528482B4B";

    //function to generate token
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException{
        return JWT.create()
                .withSubject("User details")
                .withClaim("email",email)
                .withIssuedAt(new Date())
                .withIssuer("Inet")
                .sign(Algorithm.HMAC256(secret));
    }



    //function to validate token
    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Inet")
                .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaim("email").asString();
    }
}

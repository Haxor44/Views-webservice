package com.roman.Views.security;

import com.roman.Views.services.AppUserDetailsService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
*
*
* This class is used to intercept requests and responses as it is the first to be executed and
* from the requests we can get the headers e.g. authorization header
* and modify the responses   to be able to extract jwt token.
*
*
* */
@Component
public class JWTfilter extends OncePerRequestFilter {

    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private JWTutil jwTutil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            if(jwt == null || jwt.isBlank()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT token!!!");

            } else
            {
                try
                {
                    String email = jwTutil.validateTokenAndRetrieveSubject(jwt);
                    UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,userDetails.getPassword(),userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                } catch (JWTVerificationException e)
                {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT Token!!!");
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}

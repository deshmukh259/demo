package com.springboot.demo.config;

import com.springboot.demo.config.authen.CustomAuthentication;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.*;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomSecurityFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//STEP1
        String key = request.getHeader("key");
        CustomAuthentication customAuthentication = new CustomAuthentication(false,key);


        var a = authenticationManager.authenticate(customAuthentication);
        if(a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a); //very imp,

            filterChain.doFilter(request,response);
        }
    }
}

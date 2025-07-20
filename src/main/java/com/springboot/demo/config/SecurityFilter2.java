package com.springboot.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityFilter2 {

    private final CustomSecurityFilter customSecurityFilter;
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .addFilterAt(customSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(e-> e.anyRequest().authenticated())
        .build();
    }

}

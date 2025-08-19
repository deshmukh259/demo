package com.springboot.demo.config;

import com.springboot.demo.provider.CustomAuthProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthProvider customAuthProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(customAuthProvider.supports(authentication.getClass())){
            return customAuthProvider.authenticate(authentication);
        }
throw new BadCredentialsException("No provide found");
    }
}

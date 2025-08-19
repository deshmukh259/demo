package com.springboot.demo.provider;

import com.springboot.demo.config.authen.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Value("${app.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//STEP3
        CustomAuthentication ca = (CustomAuthentication) authentication;
        String headerKey = ca.getKey();

        if(key.equals(headerKey)){
            return new CustomAuthentication(true,key);
        }
        throw  new BadCredentialsException("Invalid key");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}

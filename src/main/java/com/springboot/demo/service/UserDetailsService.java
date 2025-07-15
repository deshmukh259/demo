package com.springboot.demo.service;

import com.springboot.demo.config.secu.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.springboot.demo.model.UserDetails> byUserName = userDetailsRepository.findByUserName(username);
     return byUserName.map(SecurityUser::new).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}

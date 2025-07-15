package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class WebsecurityConfig {


//    @Bean
//    @Order(1)
//    public UserDetailsService userDetailsService2(){
//
//
//    }


//@Bean

    /// /@Order(2)
//    public UserDetailsService userDetailsService(){
//
//        var uds = new InMemoryUserDetailsManager();
//        var v1 = User.withUsername("test1")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        uds.createUser(v1);
//        return uds;
//
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

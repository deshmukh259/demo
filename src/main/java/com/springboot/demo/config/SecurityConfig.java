package com.springboot.demo.config;


import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

//  @Bean
//    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
//
//
////   http.exceptionHandling(
////           c-> c.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
////   );
//
//
//      return http.build();
//  }
//


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.authorizeHttpRequests(
//
//                c->c.requestMatchers("/api/v1/")
//                        .access(new WebExpressionAuthorizationManager("isAuthenticated()"))
//                        .anyRequest().authenticated()
//        );
//        httpSecurity.csrf(c-> c.ignoringRequestMatchers("/some/path/*"));
//        return httpSecurity.build();
//
//    }


}

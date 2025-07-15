package com.springboot.demo.config;


import org.springframework.context.annotation.Configuration;

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

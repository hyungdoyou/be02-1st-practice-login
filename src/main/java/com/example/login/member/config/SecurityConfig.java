package com.example.login.member.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {

            http.csrf().disable()
                    .authorizeHttpRequests()
                        .antMatchers("/member/login", "/member/signup", "/product/list", "/product/read", "/product/create").permitAll()
                        .antMatchers("/order/create", "/order/list").hasRole("USER")
                        .anyRequest().authenticated()
                    .and()
                        .formLogin().disable();

            return  http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

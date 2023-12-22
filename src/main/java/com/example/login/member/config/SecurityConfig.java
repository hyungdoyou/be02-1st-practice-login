package com.example.login.member.config;

import com.example.login.member.config.filter.JwtFilter;
import com.example.login.member.repository.MemberRepository;
import com.example.login.member.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Value("${jwt.secret-key}")
    private String secretKey;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    public SecurityConfig(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {

            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/member/signup").permitAll()
                    .antMatchers("/test/*").hasAuthority("USER")
                    .anyRequest().authenticated();
            http.addFilterBefore(new JwtFilter(memberService, secretKey, memberRepository), UsernamePasswordAuthenticationFilter.class);
            http.formLogin().disable();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 아예 사용 안하겠다.

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
//        try {
//
//            http.csrf().disable()
//                    .authorizeHttpRequests()
//                        .antMatchers("/member/login", "/member/signup", "/product/list", "/product/read", "/product/create").permitAll()
//                        .antMatchers("/order/create", "/order/list").hasRole("USER")
//                        .anyRequest().authenticated()
//                    .and()
//                        .formLogin().disable();
//
//            return  http.build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

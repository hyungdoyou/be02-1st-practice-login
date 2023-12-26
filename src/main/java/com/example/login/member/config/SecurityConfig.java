package com.example.login.member.config;


import com.example.login.member.config.filter.JwtFilter;
import com.example.login.member.repository.MemberRepository;
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
    private String secretKey;   // 추가

    private final MemberRepository memberRepository;
    public SecurityConfig(MemberRepository memberRepository) {
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
                    .authorizeHttpRequests()
                        .antMatchers("/member/*","/member/login", "/member/signup", "/product/list", "/product/read", "/product/create").permitAll()
                        .antMatchers("/order/create", "/order/list").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
                    .and()
                        .formLogin().disable()
                        .addFilterBefore(new JwtFilter(secretKey, memberRepository), UsernamePasswordAuthenticationFilter.class)  // 추가
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // 추가

            return  http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

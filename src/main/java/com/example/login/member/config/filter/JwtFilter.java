package com.example.login.member.config.filter;



import com.example.login.member.config.utils.JwtUtils;
import com.example.login.member.model.entity.Member;
import com.example.login.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {

    private MemberRepository memberRepository;
    @Value("${jwt.secret-key}")
    private String secretKey;

    public JwtFilter(String secretKey, MemberRepository memberRepository) {
        this.secretKey = secretKey;
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token;
        if (header!= null && header.startsWith("Bearer ")) {
            token = header.split(" ")[1];
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtUtils.getUsername(token, secretKey);

        // db에서 엔티티 조회
        Optional<Member> result = memberRepository.findByUsername(username);
        Member member = null;
        if(result.isPresent()) {
            member = result.get();
        } else {
            return;
        }
        String memberUsername = member.getUsername();
        if(!JwtUtils.validate(token, memberUsername, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 인가하는 코드
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                member, null,
                member.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}

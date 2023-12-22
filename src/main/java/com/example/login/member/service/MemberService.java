package com.example.login.member.service;

import com.example.login.member.config.utils.JwtUtils;
import com.example.login.member.model.MemberDto;
import com.example.login.member.model.MemberLoginReq;
import com.example.login.member.model.MemberLoginRes;
import com.example.login.orders.model.MemberOrdersDto;
import com.example.login.product.model.ProductReadRes;
import com.example.login.member.model.entity.Member;
import com.example.login.orders.model.entity.Orders;
import com.example.login.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
    @Value("${jwt.secret-key}")
    private String secretKey;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Signup
    public void signup(MemberLoginReq memberLoginReq) {
        if(!memberRepository.findByUsername(memberLoginReq.getUsername()).isPresent())
        memberRepository.save(Member.builder()
                        .username(memberLoginReq.getUsername())
                        .password(passwordEncoder.encode(memberLoginReq.getPassword()))
                        .authority("ROLE_USER")
                .build());
    }

    public Boolean login(MemberLoginReq memberLoginReq) {
        return JwtUtils.validate(memberLoginReq.getUsername(), memberLoginReq.getToken(), secretKey);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByUsername(username);
        Member member = null;
        if(result.isPresent()) {
            member = result.get();
        }
        return member;
    }
}

package com.example.login.member.controller;

import com.example.login.member.config.utils.JwtUtils;
import com.example.login.member.model.MemberDto;
import com.example.login.member.model.MemberLoginReq;
import com.example.login.member.model.MemberLoginRes;
import com.example.login.member.model.entity.Member;
import com.example.login.member.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Integer expiredTimeMs;
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    public MemberController(MemberService memberService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/signup")
//    public ResponseEntity signup(@RequestBody MemberLoginReq memberLoginReq) {
//        memberService.signup(memberLoginReq);
//
//        return ResponseEntity.ok().body("ok");
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public ResponseEntity login(@RequestBody MemberLoginReq memberLoginReq){
//
//        return ResponseEntity.ok().body(memberService.login(memberLoginReq));
//    }
//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public ResponseEntity login(@RequestBody MemberLoginReq memberLoginReq){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberLoginReq.getUsername(), memberLoginReq.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Member member = ((Member)authentication.getPrincipal());
//        return ResponseEntity.ok().body(MemberLoginRes.builder()
//                    .id(member.getId())
//                    .username(member.getUsername())
//            .build());
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@RequestBody MemberLoginReq memberLoginReq) {

        return ResponseEntity.ok().body(JwtUtils.generateAccessToken(memberLoginReq.getUsername(), secretKey, expiredTimeMs));
    }



    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody MemberLoginReq memberLoginReq){

        return ResponseEntity.ok().body(memberService.login(memberLoginReq));
    }
}

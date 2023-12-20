package com.example.login.member.controller;

import com.example.login.member.model.MemberDto;
import com.example.login.member.model.MemberLoginReq;
import com.example.login.member.model.MemberLoginRes;
import com.example.login.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity create(@RequestBody MemberDto memberDto) {
        memberService.create(memberDto);

        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {

        return ResponseEntity.ok().body(memberService.list());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody MemberLoginReq memberLoginReq) {

        return ResponseEntity.ok().body(memberService.login(memberLoginReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(String email, String password) {

        return ResponseEntity.ok().body(memberService.read(email, password));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(MemberDto memberDto) {
        memberService.update(memberDto);

        return ResponseEntity.ok().body("수정");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(Integer id) {
        memberService.delete(id);
        return ResponseEntity.ok().body("삭제");

    }
}

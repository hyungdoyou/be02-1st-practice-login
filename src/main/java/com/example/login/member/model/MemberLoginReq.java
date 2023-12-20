package com.example.login.member.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginReq {
    private String email;
    private String password;
}

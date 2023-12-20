package com.example.login.member.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginRes {
    private Integer id;
    private String email;
}

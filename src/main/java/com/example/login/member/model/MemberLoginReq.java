package com.example.login.member.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginReq {
    @JsonProperty("email")
    private String username;
    private String password;

    private String token;
    private String key;
}

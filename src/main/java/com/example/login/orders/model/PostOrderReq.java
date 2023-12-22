package com.example.login.orders.model;

import com.example.login.member.model.MemberLoginReq;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOrderReq {

    @JsonProperty("Member_id")
    private Integer memberId;

    @JsonProperty("Product_id")
    private Integer productId;

    private String password;

    private AuthenticationManager authenticationManager;
}

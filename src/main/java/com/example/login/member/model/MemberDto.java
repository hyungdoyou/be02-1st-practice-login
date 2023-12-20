package com.example.login.member.model;

import com.example.login.orders.model.MemberOrdersDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Integer id;
    private String email;
    private String password;

    private List<MemberOrdersDto> memberOrdersDtoList = new ArrayList<>();
//    private List<OrdersDto> orderDtoList = new ArrayList<>();
}

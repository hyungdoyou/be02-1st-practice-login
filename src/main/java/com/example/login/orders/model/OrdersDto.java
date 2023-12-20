package com.example.login.orders.model;

import com.example.login.member.model.MemberDto;
import com.example.login.member.model.MemberLoginRes;
import com.example.login.product.model.ProductReadRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDto {

    private Integer id;

//    private MemberDto memberDto;
//    private ProductReadRes productDto;

    private MemberLoginRes memberLoginRes;
    private ProductReadRes productReadRes;
}

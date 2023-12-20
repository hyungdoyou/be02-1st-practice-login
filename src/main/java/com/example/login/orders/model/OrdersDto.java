package com.example.login.orders.model;

import com.example.login.member.model.MemberDto;
import com.example.login.product.model.ProductDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDto {

    private Integer id;

    private MemberDto memberDto;
    private ProductDto productDto;
}

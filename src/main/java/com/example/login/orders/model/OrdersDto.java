package com.example.login.orders.model;

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


    private MemberLoginRes memberLoginRes;
    private ProductReadRes productReadRes;
}

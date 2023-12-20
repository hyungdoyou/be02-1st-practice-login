package com.example.login.orders.model;

import com.example.login.product.model.ProductReadRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrdersDto {
    private Integer id;

    private ProductReadRes productDto;
}
